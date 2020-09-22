package tools;

import it.sauronsoftware.jave.AudioUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.buf.ByteChunk;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import sun.nio.ch.DirectBuffer;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.channels.Channel;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @description:
 * @author: zexin.lin
 * @date: 2020/8/17 20:00
 */
@Slf4j
public class HttpIOUtils {

    private final static String BOUNDARY = UUID.randomUUID().toString()
            .toLowerCase().replaceAll("-", "");// 边界标识
    private final static String PREFIX = "--";// 必须存在
    private final static String LINE_END = "\r\n";

    private static ByteBuffer directBuffer = ByteBuffer.allocateDirect(1024);


    /**
     * nio type
     * @param inputStream
     * @param suffix
     * @return
     */
    public static File downloadInputStreamVer2(InputStream inputStream,String suffix) {
        File tmpSource;
        try {
            tmpSource = generateRandomTmpFile(suffix);
            ByteChannel ins = (ByteChannel)Channels.newChannel(inputStream);
            FileChannel fos = new FileOutputStream(tmpSource,true).getChannel();
            while ((ins.read(directBuffer)) != -1) {
                directBuffer.flip();
                fos.write(directBuffer);
                directBuffer.clear();
            }
            directBuffer.clear();
            inputStream.close();
            ins.close();
            fos.close();
            return tmpSource;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 流写入文件
     */
    @SuppressWarnings("can throw null val")
    public static File downloadInputStream(InputStream inputStream,String suffix) {

        File tmpSource = null;
        try {
            tmpSource = generateRandomTmpFile(suffix);
            FileOutputStream fos = new FileOutputStream(tmpSource,true);
            int byteCount;
            int byteRead = 0;
            byte[] bytes = new byte[1024];
            while ((byteCount = inputStream.read(bytes)) != -1) {
                fos.write(bytes, byteRead, byteCount);
            }
            inputStream.close();
            fos.close();
            return tmpSource;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }



    /**
     * 文件读出
     */
    @SuppressWarnings("can throw null val")
    public static InputStream getStreamFromFile(File f){
        try {
            return new FileInputStream(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 文件转码
     */
    public static File convertAmr(File source,String suffix)  {
        File tmpDest = generateRandomTmpFile(suffix);
        AudioUtils.convert(source, tmpDest,suffix);
        return tmpDest;
    }


    /**
     * 从企业微信获取文件
     * 企业微信回复类型
     *    HTTP/1.1 200 OK
     *    Connection: close
     *    Content-Type: image/jpeg
     *    Content-disposition: attachment; filename="MEDIA_ID.jpg"
     *    Date: Sun, 06 Jan 2013 10:20:18 GMT
     *    Cache-Control: no-cache, must-revalidate
     *    Content-Length: 339721
     * @param url
     * @param queryParas
     * @param headers
     * @return
     */
    public static MultipartFile getResourceStream(String url, Map<String, String> queryParas, Map<String, String> headers) {
        HttpURLConnection conn = null;
        try {
            conn = HttpUtils.getHttpConnection(HttpUtils.buildUrlWithQueryString(url, queryParas), "GET", headers);
            conn.connect();
            if(StringUtils.isEmpty(conn.getHeaderField("Content-disposition"))){
                log.info("非法的resourceId,URL:{}",url);
                return null;
            }
            String nameBuff = conn.getHeaderField("Content-disposition").split(";")[1];
            String fileName = "noName";
            Pattern p = Pattern.compile("\"(.*?)\"");
            Matcher m = p.matcher(nameBuff);
            if (m.find()) {
                fileName = m.group().replace("\"", "");
            }
            try {
                InputStream is = conn.getInputStream();
                return new MockMultipartFile(fileName,is);
            } catch (IOException e) {
                log.info("无法获取到企业微信方的文件流");
                e.printStackTrace();
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *  POST Multipart Request
     *  @Description:
     *  @param requestUrl 请求url
     *  @param requestText 请求参数
     *  @param requestFile 请求上传的文件
     *  @return
     *  @throws Exception
     */
    public static String uploadResourceToOss(String requestUrl,
                                             Map<String, String> requestText, Map<String, MultipartFile> requestFile) throws Exception{
        HttpURLConnection conn = null;
        InputStream input = null;
        OutputStream os = null;
        BufferedReader br = null;
        StringBuffer buffer = null;
        try {
            URL url = new URL(requestUrl);
            conn = (HttpURLConnection) url.openConnection();

            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setConnectTimeout(1000 * 10);
            conn.setReadTimeout(1000 * 10);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Accept", "*/*");
            conn.setRequestProperty("Connection", "keep-alive");
            conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
            conn.setRequestProperty("Charset", "UTF-8");
            conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);
            conn.connect();

            // 往服务器端写内容 也就是发起http请求需要带的参数
            os = new DataOutputStream(conn.getOutputStream());
            // 请求参数部分
            writeParams(requestText, os);
            // 请求上传文件部分
            writeFile(requestFile, os);
            // 请求结束标志
            String endTarget = PREFIX + BOUNDARY + PREFIX + LINE_END;
            os.write(endTarget.getBytes());
            os.flush();

            // 读取服务器端返回的内容
            System.out.println("ResponseCode:" + conn.getResponseCode()
                    + ",ResponseMessage:" + conn.getResponseMessage());
            if(conn.getResponseCode()==200){
                input = conn.getInputStream();
            }else{
                input = conn.getErrorStream();
            }

            br = new BufferedReader(new InputStreamReader( input, "UTF-8"));
            buffer = new StringBuffer();
            String line = null;
            while ((line = br.readLine()) != null) {
                buffer.append(line);
            }
            //......
            log.info("返回报文:" + buffer.toString());

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new Exception(e);
        } finally {
            try {
                if (conn != null) {
                    conn.disconnect();
                    conn = null;
                }

                if (os != null) {
                    os.close();
                    os = null;
                }

                if (br != null) {
                    br.close();
                    br = null;
                }
            } catch (IOException ex) {
                log.error(ex.getMessage(), ex);
                throw new Exception(ex);
            }
        }
        return buffer.toString();
    }

    /**
     * 对post参数进行编码处理并写入数据流中
     * @throws Exception
     *
     * @throws IOException
     *
     * */
    private static void writeParams(Map<String, String> requestText,
                                    OutputStream os) throws Exception {
        try{
            String msg = "请求参数部分:\n";
            if (requestText == null || requestText.isEmpty()) {
                msg += "空";
            } else {
                StringBuilder requestParams = new StringBuilder();
                Set<Map.Entry<String, String>> set = requestText.entrySet();
                Iterator<Map.Entry<String, String>> it = set.iterator();
                while (it.hasNext()) {
                    Map.Entry<String, String> entry = it.next();
                    requestParams.append(PREFIX).append(BOUNDARY).append(LINE_END);
                    requestParams.append("Content-Disposition: form-data; name=\"")
                            .append(entry.getKey()).append("\"").append(LINE_END);
                    requestParams.append("Content-Type: text/plain; charset=utf-8")
                            .append(LINE_END);
                    requestParams.append("Content-Transfer-Encoding: 8bit").append(
                            LINE_END);
                    requestParams.append(LINE_END);// 参数头设置完以后需要两个换行，然后才是参数内容
                    requestParams.append(entry.getValue());
                    requestParams.append(LINE_END);
                }
                os.write(requestParams.toString().getBytes());
                os.flush();

                msg += requestParams.toString();
            }

            //System.out.println(msg);
        }catch(Exception e){
            log.error("writeParams failed", e);
            throw new Exception(e);
        }
    }

    /**
     * 对post上传的文件进行编码处理并写入数据流中
     *
     * @throws IOException
     *
     * */
    private static void writeFile(Map<String, MultipartFile> requestFile,
                                  OutputStream os) throws Exception {
        InputStream is = null;
        try{
            String msg = "请求上传文件部分:\n";
            if (requestFile == null || requestFile.isEmpty()) {
                msg += "空";
            } else {
                StringBuilder requestParams = new StringBuilder();
                Set<Map.Entry<String, MultipartFile>> set = requestFile.entrySet();
                Iterator<Map.Entry<String, MultipartFile>> it = set.iterator();
                while (it.hasNext()) {
                    Map.Entry<String, MultipartFile> entry = it.next();
                    if(entry.getValue() == null){//剔除value为空的键值对
                        continue;
                    }
                    requestParams.append(PREFIX).append(BOUNDARY).append(LINE_END);
                    requestParams.append("Content-Disposition: form-data; name=\"")
                            .append(entry.getKey()).append("\"; filename=\"")
                            .append(entry.getValue().getName()).append("\"")
                            .append(LINE_END);
                    requestParams.append("Content-Type:")
                            .append(entry.getValue().getContentType())
                            .append(LINE_END);
                    requestParams.append("Content-Transfer-Encoding: 8bit").append(
                            LINE_END);
                    requestParams.append(LINE_END);// 参数头设置完以后需要两个换行，然后才是参数内容

                    os.write(requestParams.toString().getBytes());
                    os.write(entry.getValue().getBytes());

                    os.write(LINE_END.getBytes());
                    os.flush();

                    msg += requestParams.toString();
                }
            }
            //System.out.println(msg);
        }catch(Exception e){
            log.error("writeFile failed", e);
            throw new Exception(e);
        }finally{
            try{
                if(is!=null){
                    is.close();
                }
            }catch(Exception e){
                log.error("writeFile FileInputStream close failed", e);
                throw new Exception(e);
            }
        }
    }


    private static File generateRandomTmpFile(String suffix)  {
        String dir = "resources/tmp/";
        String fileName = dir+UUIDGenerator.getShortenUUID()+"."+suffix;
        File dirTar = new File(dir);
        File f = new File(fileName);
        if(!dirTar.exists()) {
            dirTar.mkdirs();
        }
        if(!f.exists()){
            try {
                f.createNewFile();
            } catch (IOException e) {
                if(f.exists()){
                    f.delete();
                }
            }
        }
        return f;
    }


}
