package com.weixin.heyawego.app.common.utils;



import org.apache.http.*;

import java.net.URLEncoder;
import java.nio.charset.Charset;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

/**
 * Created by admin on 2018/3/6.
 */
public class HttpClient {

    /**
     * 模拟请求
     *
     * @param url       资源地址
     * @param map   参数列表
     * @param encoding  编码
     * @return
     * @throws ParseException
     * @throws IOException
     */
    public static String send(String url, Map<String,String> map, String encoding) throws ParseException, IOException{
        String body = "";

        //创建httpclient对象
        CloseableHttpClient client = HttpClients.createDefault();
        //创建post方式请求对象
        HttpPost httpPost = new HttpPost(url);

        //装填参数
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        if(map!=null){
            for (Map.Entry<String, String> entry : map.entrySet()) {
                nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
        }
        //设置参数到请求对象中
        httpPost.setEntity(new UrlEncodedFormEntity(nvps, encoding));
        System.out.println("\n/****************** send ***************/");
        System.out.println("请求地址："+url);
        System.out.println("请求参数："+nvps.toString());

        //设置header信息
        //指定报文头【Content-type】、【User-Agent】
        httpPost.setHeader("Content-type", "application/x-www-form-urlencoded");
        httpPost.setHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

        //执行请求操作，并拿到结果（同步阻塞）
        CloseableHttpResponse response = client.execute(httpPost);
        //获取结果实体
        HttpEntity entity = (HttpEntity) response.getEntity();
        if (entity != null) {
            //按指定编码转换结果实体为String类型
            body = EntityUtils.toString((HttpEntity) entity, encoding);
        }
        EntityUtils.consume((HttpEntity) entity);
        //释放链接
        response.close();
        client.close();
        return body;
    }

    /**
     * @Author : xuchang
     * @Description :     GET方式下载
     * @Param : url       路径
     * @Param : filePath  保存地址
     * @Return : java.util.Map<java.lang.String,java.lang.String>
     * @Date : 2018/5/2 9:46
     */
    public static Map<String,String> download(String url,String filePath) {
        Map<String,String> map = new HashMap<>();
        map.put("status" , "success");
        //创建httpclient对象
        CloseableHttpClient client = HttpClients.createDefault();
        //创建GET请求对象
        HttpGet httpGet = new HttpGet(url);
        try{
            //执行请求操作，并拿到结果（同步阻塞）
            CloseableHttpResponse response = client.execute(httpGet);
            HttpEntity entity = response.getEntity() ;
            InputStream is = entity.getContent() ;
            if(null != filePath){
                filePath = filePath+getFileName(response) ;
                System.out.println("\n---------> downloadPath : "+filePath + "\n");
                File file = new File(filePath);
                if(!file.getParentFile().isDirectory()){
                    file.getParentFile().mkdirs();
                }
                FileOutputStream os = new FileOutputStream(file);
                byte[] buffer = new byte[1024] ;
                int ch = 0 ;
                while((ch = is.read(buffer))!=-1){
                    os.write(buffer,0 , ch);
                }
                map.put("filePath",filePath);
                is.close();
                os.flush();
                os.close();
            }
            response.close();
            client.close();
        }catch(IOException e){
            e.printStackTrace();
            map.put("status" , "exception");
        }
        return map;
    }

    /**
     * @Author : xuchang
     * @Description :     从CloseableHttpResponse中获取文件名字
     * @Param : response
     * @Return : java.lang.String
     * @Date : 2018/5/2 9:48
     */
    private static String getFileName(CloseableHttpResponse response){
        Header header = response.getFirstHeader("Content-Disposition");
        String fileName = DateUtils.formatDate(new Date(),"yyyyMMddHHmmss") ;
        if( null != header){
            HeaderElement[] value = header.getElements() ;
            if(1 == value.length){
                NameValuePair param = value[0].getParameterByName("filename");
                if( null != param){
                    fileName = param.getValue() ;
                }
            }
        }
        return fileName ;
    }

    /**
     * @Author : xuchang
     * @Description :  上传接口
     * @Param : url
     * @Param : filePath
     * @Return : java.lang.String
     * @Date : 2018/5/3 14:21
     */
    public static String upload(String url,String filePath) throws IOException {
        String rtnStr = "" ;
        System.out.println("\n/******************************  UPLOAD START****************************/");
        //创建httpclient对象
        CloseableHttpClient client = HttpClients.createDefault();
        //创建POST请求对象
        HttpPost httpPost = new HttpPost(url);
        File file = new File (filePath);
        FileBody fileBody = new FileBody(file);
        HttpEntity httpEntity = MultipartEntityBuilder.create().addPart("file",fileBody).build();
        httpPost.setEntity(httpEntity);
        System.out.println("/**************executiong request***************/");
        System.out.println(httpPost.getRequestLine());

        CloseableHttpResponse response = client.execute(httpPost);
        System.out.println("/**************response line***************/");
        System.out.println(response.getStatusLine());
        HttpEntity respEntity = response.getEntity() ;
        if( null != respEntity){
            System.out.println("response content length : "+respEntity.getContentLength());
            rtnStr = EntityUtils.toString(respEntity,"UTF-8") ;
            System.out.println(rtnStr);
        }
        EntityUtils.consume(respEntity);
        response.close();
        client.close();
        System.out.println("/******************************  UPLOAD END ****************************/\n");
        return rtnStr ;
    }

    /**
     * @Author : xuchang
     * @Description :  post方式提交普通json参数
     * @Param : url
     * @Param : param
     * @Param : body
     * @Param : encoding
     * @Return : java.lang.String
     * @Date : 2018/5/3 14:20
     */
    public static String stringBodyPost(String url , Map<String ,String> param , String body , String encoding){
        //创建httpclient对象
        CloseableHttpClient client = HttpClients.createDefault();
        CloseableHttpResponse response = null ;
        //创建post方式请求对象
        StringBuilder sb = new StringBuilder() ;
        try {
            if(null != param && 0 != param.size()){
                for(String key : param.keySet()){
                    sb.append(key + "=" + URLEncoder.encode(param.get(key),encoding)+"&");
                }
            }
            HttpPost httpPost = new HttpPost(url+sb.toString());
            if(StringUtils.isNotEmpty(body)){
                StringEntity stringEntity = new StringEntity(body,ContentType.APPLICATION_JSON);
                httpPost.setEntity(stringEntity);
            }
            response = client.execute(httpPost);
            HttpEntity entity = response.getEntity() ;
            return EntityUtils.toString(entity,encoding);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch(IOException e){
            e.printStackTrace();
        }finally {
            try {
                client.close();
                if(response != null){
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null ;
    }

}
