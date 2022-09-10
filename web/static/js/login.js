window.onload=function() {
    var vue = new Vue({
        el: "#content",
        data: {
            showErroFlag:false,
        },
        methods: {
            showErro:function () {
                axios({
                    method:"POST",
                    url:"user.do",
                    params:{
                        operate:'showErro'
                    }
                })
                    .then(function (value) {
                        var data = value.data ;
                        vue.showErroFlag=data.showErroFlag;
                        if(vue.showErroFlag==true){
                            layer.alert('账号或密码不正确', {icon: 5,title:" "});
                        }
                        layui.use(['form'], function(){
                            var form = layui.form;
                            form.render();
                        })

                    })
                    .catch(function (reason) {  });
            },
        },
        created: function(){
            },
        mounted: function () {
            this.showErro();
        },

        updated:function(){
            layui.use(['form'], function(){
                var form = layui.form;
                form.render();
            })
        },
    });
}