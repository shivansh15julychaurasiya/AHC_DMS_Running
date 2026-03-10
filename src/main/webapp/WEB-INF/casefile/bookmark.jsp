<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>PDF Bookmarks</title>

<style>

body{
    font-family: Arial;
    margin:0;
    padding:0;
}

#bookmarkContainer{
    padding:10px;
}

#bookmarkContainer ul{
    list-style:none;
    padding-left:10px;
}

#bookmarkContainer li{
    padding:6px;
    cursor:pointer;
    border-bottom:1px solid #ddd;
}

#bookmarkContainer li:hover{
    background:#f1f1f1;
}

</style>

</head>
<body>

<h3 style="margin:0;padding:10px;background:#eee;">
PDF Bookmarks
</h3>

<div id="bookmarkContainer">
    <ul id="bookmarkList"></ul>
</div>


<script>

let bookmarkData = [];

window.addEventListener("message", function(event){

    console.log("Received in bookmark.jsp:", event.data);

    if(event.data.type === "PDF_BOOKMARKS"){

        bookmarkData = event.data.bookmarks;

        displayBookmarks(bookmarkData);

    }

});


function displayBookmarks(bookmarks){

    const list = document.getElementById("bookmarkList");

    list.innerHTML = "";

    bookmarks.forEach(function(bm){

        const li = document.createElement("li");

        li.innerText = bm.title;

        li.onclick = function(){
            goToBookmark(bm.pageIndex); // ✅ real page
        };

        list.appendChild(li);

    });
}


function goToBookmark(index){

    console.log("Sending bookmark index:", index);

    const iframe = document.getElementById("pdfFrame");

    if(!iframe){
        console.log("ERROR: pdfFrame NOT FOUND");
        return;
    }

    iframe.contentWindow.postMessage({
        type:"GO_TO_BOOKMARK",
        index:index
    }, "*");

}

</script>


</body>
</html>