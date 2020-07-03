(function () {
    var table = function (options) {
        //table("options") 或者 new table("options")都可以使用table方法
        if(!(this instanceof table)){return new table(options)};

        // 参数合并 extend方法体在下面
        this.options = this.extend({
            lineNo:true
        }, options);
        this.init();
        //初始化
    };
    table.prototype = {
        init: function () {
            this.event();
        },
        // 参数合并方法体
        extend: function (obj, obj2) {
            for (var key in obj2) {
                obj[key] = obj2[key];
                //    确保参数唯一
            }
            return obj
        },
        event:function () {
            var _this = this;

            //方法调用前的回调
            _this.options.open&&_this.options.open();

            //此处执行方法体，使用 this.options.x\this.options.y\this.options.z进行访问


            //打印参数
            console.log(this.options.colume)


            var tableDate;
            //根据url获取数据
            ajax({
                type:"GET",
                url:this.options.dataUrl,
                async:false,
                success:function (data) {
                    tableDate=JSON.parse(data)
                }
            })
            debugger
            //创建表格
            var table=document.createElement('table');
            table.setAttribute("border","1")
            //创建表头
            let trHead=document.createElement('tr');

            for (let j=0;j<_this.options.columeAlias.length;j++){

                let td=document.createElement('td');
                td.innerText=_this.options.columeAlias[j]
                trHead.appendChild(td)
            }

            table.appendChild(trHead)



            for (let i=0;i<tableDate.length;i++){
                let tr=document.createElement('tr');

                for (let j=0;j<_this.options.colume.length;j++){

                    let td=document.createElement('td');
                    td.innerText=tableDate[i][_this.options.colume[j]]
                    tr.appendChild(td)
                }

                table.appendChild(tr)
            }

            var showArea = $("datatable")
            showArea.appendChild(table)


            // 方法结束的回调
            _this.options.close&&_this.options.close();
        }
    }
    //暴露对象
    window.table = table;
}());

