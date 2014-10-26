<script type="text/javascript">
    <!--
    function confirmation() {
        var answer = confirm("A user '${loginUser.username}' has logined,will you let it logout?");
        var loc=window.location;
        var baseURL=loc.protocol+"//"+loc.host;
        if (answer) {
            //alert(loc.protocol+"\n"+loc.host+"\n"+loc.hostname+"\n"+loc.href +"\n"+loc.pathname+"\n"+loc.search);
            alert(baseURL+"/forceLogout");
            window.location =baseURL+"/admin/forceLogout"
        }
        else {
           // alert("Thanks for sticking around!")
            window.location=baseURL;
        }
    }
    confirmation();
    //-->
</script>