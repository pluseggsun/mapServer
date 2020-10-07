$().ready(function () {
    validateRule();
});

$.validator.setDefaults({
    submitHandler: function () {
        update();
    }
});


function update() {
	if($("#addressno").val() == ""){
        parent.layer.msg("コードが既に存在しますので、変更してください。");
        return false;
	}
    
    $.ajax({
		cache : true,
		type : "POST",
		url : "/code/state/update",
		data : $('#signupForm').serialize(), // 你的formid
		async : false,
        error: function (request) {
            parent.layer.msg("コードが既に存在しますので、変更してください。");
        },
        success: function (data) {
            if (data.code == 0) {
                parent.layer.msg("更新処理を完了しました。");
                parent.reLoad();
                var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
                parent.layer.close(index);

            } else {
                parent.layer.msg("コードが既に存在しますので、変更してください。");
            }

        }
    });

}

function validateRule() {
    var icon = "<i class='fa fa-times-circle'></i> ";
    $("#signupForm").validate({
    })
}
