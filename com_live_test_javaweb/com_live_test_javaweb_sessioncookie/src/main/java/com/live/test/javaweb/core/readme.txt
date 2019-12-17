
*fileupload(文件上传)

方式一:
基于 原生requset本身的getParts()实现文件上传
Collection<Part> coll = req.getParts();
ServletInputStream sin = req.getInputStream();
响应给前端文件的绝对路径
eg:{"msg":"","code":0,"data":"/opt/saasUpload/HeadImage/sxn002/2019_12_17_14_44_15_015-13f1f4fb-5446-4199-9e4d-caae89b8964a-11.PNG"}

方式二:
基于 fileUpload(Apache Commons FileUpload)实现文件上传
上传文件,并响应给前端一个可以浏览器访问的url
eg:{"code":0,"data":{"fileUrl":"http://192.168.1.53:8080/core-0.0.1-SNAPSHOT/imUpload/2019/12/17/dfd9570b-92bd-4bf3-833c-6a04bd6745e6_11.PNG"}}
