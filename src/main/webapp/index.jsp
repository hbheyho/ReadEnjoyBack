<!DOCTYPE html>
<html lang="en">
<body>
书籍上传测试：
<form name="form1" action="/book/upload.do" method="post" enctype="multipart/form-data">
    <input type="text" name="bookISBN">
    <input type="file" name="upload_file">
    <input type="submit" value="上传文件">
</form>
书籍下载测试：
<a href="/book/download.do?bookName=9300f4ad-146d-4e3b-b7d6-0f7f45c365a0.pdf">下载</a>

图片上传测试：
<form name="form1" action="/user/upload_img.do" method="post" enctype="multipart/form-data">
    <input type="file" name="upload_img">
    <input type="submit" value="富文本文件上传">
</form>
</body>
</html>
