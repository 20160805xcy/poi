<%@page contentType="text/html; charset=UTF-8" %>
<html>
<script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
<body>
<h2>Hello World!</h2>

<form method="POST" enctype="multipart/form-data" action="/excel/insertEntryByExcel">
    文件：<input type="file" name="excelUploadFile" id="fileId"/>
    <input type="submit" value="上传"/>
</form>

<button id="test">点我 查看文件大小,并检查项目通不通</button>

<script>
    $("#test").click(function () {

        var files = document.getElementById('fileId').files;
        var fileSize = 0;
        if (files.length != 0) {
            fileSize = files[0].size;
            alert("上传文件的大小为:" + fileSize + "B");
        }

        if (fileSize > 1048576) {
            alert("文件不能大于 1M ");
            return false;
        }

        $.ajax({
            url: "excel/test",
            data: "name=xcy",
            success: function (data) {
                alert("返回的数据为:" + data)
                console.info(data);
            }
        });
    });


</script>
</body>

</html>
