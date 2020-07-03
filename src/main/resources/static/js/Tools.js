function $(id) {
    return document.getElementById(id)
}

function ajax(data) {
    var xmlHttp=new XMLHttpRequest();
    xmlHttp.onreadystatechange=function (ev) {
        if(xmlHttp.readyState==4&&xmlHttp.status==200){
            var res=xmlHttp.responseText;
            data.success(res);
        }
    }

    xmlHttp.open(data.type,data.url,data.async)

    xmlHttp.send();
}