$().ready(function () {
    validateRule();
});

$.validator.setDefaults({
    submitHandler: function () {
        update();
    }
});


function update() {
//	if($("#addressno").val() == ""){
//        parent.layer.alert("Connection error");
//        return false;
//	}
	
	if($("#registernoimage").val() == ""){
        parent.layer.alert("変更してください。");
        return false;
	}
	var formData = new FormData();
//    formData.append("addressno", $("#addressno").val());//x
    formData.append("registernoimage", $("#registernoimage").val());//y
    
//    formData.append("googlemapmarkx", $("#googlemapmarkx").val());//x
//    formData.append("googlemapmarky", $("#googlemapmarky").val());//y
//    formData.append("imagenameold", $("#imagenameold").val());//写真名（旧）
//    formData.append("imagenamenew", $("#imagenamenew").val());//写真名（新）
    formData.append("imageresoold", $("#imageresoold").val());//写真サイズ（原）
    formData.append("imageresonew", $("#imageresonew").val());//写真サイズ（新
    formData.append("imageyears", $("#imageyears").val());//写真年代
    formData.append("imagecontent", $("#imagecontent").val());//写真説明
    formData.append("imagestate", $("#imagestate").val());//写真状態
    formData.append("address1", $("#address1").val());//住所
    formData.append("address2", $("#address2").val());//住所
    formData.append("address3", $("#address3").val());//住所
    formData.append("address4", $("#address4").val());//住所
    formData.append("address5", $("#address5").val());//住所
    formData.append("addressname", $("#addressname").val());//住所説明
//    formData.append("postmail", $("#postmail").val());//郵便番号
//    formData.append("addresscontent", $("#addresscontent").val());//住所説明

    var fileLength = document.getElementById('file').files.length;
    for(i =0 ; i < fileLength; i++){
        formData.append('file', document.getElementById('file').files[i]);
    }
    
//    var imagestateText = $("#imagestate").find("option:selected").text();
//    imagestateText == "確認済" || imagestateText == "公開済"
    var imagestateText = $("#imagestate").find("option:selected").val();
    
    if((imagestateText == "30" || imagestateText == "40") && 
    		($("#address2").val() == "" || $("#address3").val() == "" || $("#address4").val() == ""  || $("#address5").val() == "") ){
    	
    	var subLeng = $("button[type='submit']").length;
    	if(subLeng > 1){
    		$($("button[type='submit']")[subLeng-1]).attr("disabled",false);
    	} else {
    		$($("button[type='submit']")[0]).attr("disabled",false);
    	}
    	
    	layer.alert('住所（町）、住所（丁目）、住所（番地）、住所（号）を入力してください。', {
    			title: "",
    		});
    	return false;
    }
    
    if($("#address2").val() == "" || $("#address3").val() == "" 
    	 || $("#address4").val() == ""  || $("#address5").val() == "" ){
    	
    	var subLeng = $("button[type='submit']").length;
    	if(subLeng > 1){
    		$($("button[type='submit']")[subLeng-1]).attr("disabled",false);
    	} else {
    		$($("button[type='submit']")[0]).attr("disabled",false);
    	}
    	
    	 layer.confirm('住所（町）、住所（丁目）、住所（番地）、住所（号）のいずれかが入力されないので、写真情報に紐づかない。 よろしいでしょうか？', {
    			title: "",
    	        btn: ['はい', 'いいえ']
    	 } , function () {
    		    $.ajax({
    		    	type: "POST",
    		        async:false,//同步请求
    		        processData: false,// 不处理发送的数据
    		        contentType: false,// 不设置Content-Type请求头
    		        dataType: "json",
    		        url: "/sys/update",
    		        data: formData,// 你的formid
    		        error: function (request) {
    		            parent.layer.alert("変更してください。");
    		        },
    		        success: function (data) {
    		            if (data.code == 0) {
    		                parent.layer.msg("更新処理を完了しました。");
    		                parent.reLoad();
    		                var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
    		                parent.layer.close(index);

    		            } else {
    		                parent.layer.msg("更新処理を完了しました。");
    		            }

    		        }
    		    });
    	    })
    } else {

        $.ajax({
        	type: "POST",
            async:false,//同步请求
            processData: false,// 不处理发送的数据
            contentType: false,// 不设置Content-Type请求头
            dataType: "json",
            url: "/sys/update",
            data: formData,// 你的formid
            error: function (request) {
                parent.layer.alert("Connection error");
            },
            success: function (data) {
                if (data.code == 0) {
                    parent.layer.msg("更新処理を完了しました。");
                    parent.reLoad();
                    var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
                    parent.layer.close(index);

                } else {
                    parent.layer.msg("更新処理を完了しました。");
                }

            }
        });
    }

}

function validateRule() {
    var icon = "<i class='fa fa-times-circle'></i> ";
    $("#signupForm").validate({
    })
}



function updmap() {
	if($("#addressno").val() == ""){
        parent.layer.alert("Connection error");
        return false;
	}
	
	if($("#registernoimage").val() == ""){
        parent.layer.alert("Connection error");
        return false;
	}
	var formData = new FormData();

    formData.append("addressno", $("#addressno").val());//x
    formData.append("registernoimage", $("#registernoimage").val());//y
    
    formData.append("googlemapmarkx", $("#googlemapmarkx").val());//x
    formData.append("googlemapmarky", $("#googlemapmarky").val());//y
//    formData.append("imagenameold", $("#imagenameold").val());//写真名（旧）
//    formData.append("imagenamenew", $("#imagenamenew").val());//写真名（新）
//    formData.append("imageresoold", $("#imageresoold").val());//写真サイズ（原）
//    formData.append("imageresonew", $("#imageresonew").val());//写真サイズ（新
//    formData.append("imageyears", $("#imageyears").val());//写真年代
//    formData.append("imagecontent", $("#imagecontent").val());//写真説明
//    formData.append("imagestate", $("#imagestate").val());//写真状態
//    formData.append("address1", $("#address1").val());//住所
//    formData.append("address2", $("#address2").val());//住所
//    formData.append("address3", $("#address3").val());//住所
//    formData.append("address4", $("#address4").val());//住所
//    formData.append("address5", $("#address5").val());//住所
//    formData.append("postmail", $("#postmail").val());//郵便番号
    formData.append("addresscontent", $("#addresscontent").val());//住所説明

//    var fileLength = document.getElementById('file').files.length;
//    for(i =0 ; i < fileLength; i++){
//        formData.append('file', document.getElementById('file').files[i]);
//    }
    
    $.ajax({
    	type: "POST",
        async:false,//同步请求
        processData: false,// 不处理发送的数据
        contentType: false,// 不设置Content-Type请求头
        dataType: "json",
//        url: "/sys/update",
        data: formData,// 你的formid
        error: function (request) {
            parent.layer.alert("Connection error");
        },
        success: function (data) {
            if (data.code == 0) {
                parent.layer.msg("更新処理を完了しました。");
                parent.reLoad();
                var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
                parent.layer.close(index);

            } else {
                parent.layer.msg("更新処理を完了しました。");
            }

        }
    });

}