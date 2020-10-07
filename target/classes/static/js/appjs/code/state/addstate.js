$().ready(function () {
    validateRule();
});

$.validator.setDefaults({
    submitHandler: function () {
        save();
    }
});


function save() {
    $.ajax({
		cache : true,
		type : "POST",
		url : "/code/state/save",
		data : $('#signupForm').serialize(), // 你的formid
		async : false,
        error: function (request) {
            parent.layer.msg("コードが既に存在しますので、変更してください。");
        },
        success: function (data) {
            if (data.code == 0) {
                parent.layer.msg("登録処理を完了しました");
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
        rules: {
            name: {
                required: true
            }
        },
        messages: {
            name: {
                required: icon + ""
            }
        }
    })
}