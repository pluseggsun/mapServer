var prefix = "/sys"

$(function () {
    load();
	getcityinfo();
	getimagecode();
	getstatuscode();
	
	laydate.render({
	  elem: '#startime',
	  lang: 'en'
	}); 

	laydate.render({
	  elem: '#endtime',
	  lang: 'en'
	}); 

});

function load() {
    $('#exampleTable').bootstrapTable({
                method: 'get', //  get or post
                url: prefix + "/list", // 
                //	showRefresh : true,
                //	showToggle : true,
                //	showColumns : true,
                iconSize: 'outline',
                toolbar: '#exampleToolbar',
                striped: true, // 
                dataType: "json", // 
                pagination: true, // 
                // queryParamsType : "limit",
                singleSelect: false, // 
                // contentType : "application/x-www-form-urlencoded",
                pageSize: 10, // 
                pageNumber: 1, // 
                //search : true, // 
                showColumns: false, // 
                sidePagination: "server", // 
                queryParams: function (params) {
                	var stateArray = [];
                    $("input:checkbox:checked[name=imagestate]").each(function(index,element){
                        stateArray.push(element.value);
                    });
                    return {
                        limit: params.limit,
                        offset: params.offset,
                        startime: $('#startime').val(),
                        endtime: $('#endtime').val(),
                        address1: $('#address1').val(),
                        address2: $('#address2').val(),
                        address3: $('#address3').val(),
                        address4: $('#address4').val(),
                        address5: $('#address5').val(),
                        image: $('#image').val(),
//                        postmail: $('#postmail').val(),
                        imageyears: $('#imageyears').val(),
                        addressname: $('#addressname').val(),
                        status: stateArray.join(",")
                    };
                },
                // 
                // queryParamsType = 'limit' 
                // limit, offset, search, sort, order 
                // pageSize, pageNumber, searchText, sortName,
                // sortOrder.
                columns: [
                	{
                        field: 'number',
                        title: '項番',
                        formatter: function (value, row, index) {
                            return index + 1;
                        }
                    },
//                    {
//                        field: 'registerno',
//                        title: '項番'
//                        visible: false
//                    },
                    {
                        field: 'registernoimage',
                        title: '項番',
                        visible: false
                    },
                    {
                        field: 'addressname',
                        title: '住所名称',
                        formatter: function (value, row, index, field) {
                        	if(row.addressname == null || row.addressname == ""){
                        		return "";
                        	}else{
                        		return row.addressname;
                        	}
                        }
                    },
                    {
                        field: 'address',
                        title: '住所',
                        formatter: function (value, row, index, field) {
                        	if(row.addressno == null || row.addressno == ""){
                        		return "";
                        	}else{
                        		return "<a href='#' onclick='updmap("+ row.registernoimage + ")' >"+row.cityName + row.townName + row.address3 +"丁目"+ row.address4 +"番"+ row.address5+"号"+"</a>";
                        	}
//                        	return row.cityName + row.townName + row.address3 +"丁目"+ row.address4 +"番"+ row.address5+"号";
                        }
                        
                    },
//                    {
//                        field: 'postmail',
//                        title: '郵便'
//                    },
                    {
                        field: 'image',
                        title: '写真名称'
                    },
                    {
                        field: 'image',
                        title: '写真',
                        formatter: function (value, row, index, field) {
                        	return "<a href='#' onclick='info("+ row.registernoimage + ")' ><img style='width:300;height:40px;' src='/upload/images/"+row.image+"' /></a>";
                        }
                    },
                    {
                        field: 'imageyearsname',
                        title: '写真年代',
                        formatter: function (value, row, index, field) {
                        	if(row.imageyearsname == null || row.imageyearsname == ""){
                        		return "";
                        	}else{
                        		return row.imageyearsname;
                        	}
                        }
                    },
                    {
                        field: 'imagestatename',
                        title: '写真状態',
                        align: 'center'
                    },
                    {
                        field: 'imagecontent',
                        title: '写真説明'
                    },
                    {
                        field: 'recInsTs',
                        title: '登録日付',
                        formatter: function (value, row, index) {
                        	return value.substr(0,10)
                        }
                    },
                    {
                        title: '',
                        field: 'id',
                        align: 'center',
                        formatter: function (value, row, index) {
                            var e = '<a class="btn btn-primary btn-sm ' + s_edit_h + '" href="#" mce_href="#" title="変更" onclick="edit(\''
                                + row.registernoimage
                                + '\')"><i class="fa fa-edit">変更</i></a> ';
                            return e;
                        }
                    },
                    {
                        title: '',
                        field: 'id',
                        align: 'center',
                        formatter: function (value, row, index) {
                            var d = '<a class="btn btn-warning btn-sm ' + s_remove_h + '" href="#" title="削除"  mce_href="#" onclick="remove(\''
                                + row.registernoimage
                                + '\')"><i class="fa fa-remove">削除</i></a> ';
                            return d;
                        }
                    }]
            });
}

function reLoad() {
//	load();
	var startime = $('#startime').val();
	var endtime = $('#endtime').val();
	var flag = false;
	if(startime != "" && endtime != "" ){
		if( endtime < startime ){
			layer.msg('登録日付（終了）は登録日付（開始）以前の日付を入力してください。', {icon: 0});
			return flag;
		}
//		if( endtime < startime ){
//			layer.msg('登録日付（終了）は登録日付（開始）以前の日付を入力してください。', {icon: 1});
//			return flag;
//		}
	}
	
    $('#exampleTable').bootstrapTable('refresh');
}

function add() {
    layer.open({
        type: 2,
        title: '新規登録',
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['1130px', '854px'],
        content: prefix + '/add' // iframe的url
    });
}

function edit(id) {
    layer.open({
        type: 2,
        title: '変更',
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['1130px', '854px'],
        content: prefix + '/edit/' + id // iframe的url
    });
}

function updmap(id) {
    layer.open({
        type: 2,
        title: '住所座標入力',
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['1130px', '454px'],
        content: prefix + '/updmap/' + id // iframe的url
    });
}

function info(id) {
    layer.open({
        type: 2,
        title: '一覧',
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['1130px', '854px'],
        content: prefix + '/info/' + id // iframe的url
    });
}

function remove(id) {
    layer.confirm('レコードを削除してよろしいでしょうか？', {
		title: "",
        btn: ['確定', 'キャンセル']
    }, function () {
        $.ajax({
            url: prefix + "/remove",
            type: "post",
            data: {
                'id': id
            },
            success: function (r) {
                if (r.code == 0) {
                    layer.msg("削除しました。");
                    reLoad();
                } else {
                    layer.msg("削除しました。");
                }
            }
        });
    })
}

function getcityinfo(){
	$.ajax({
		url : '/base/getcityinfo',
		success : function(data) {
//		 	var ctiy =  "<option value=''>選択</option>";
		 	var ctiy =  "";
			for(var i = 0; i < 1; i++){
				ctiy += "<option value='"+data[i].city_id+"'>"+data[i].city_name+"</option>";
			}
			$("#address1").append(ctiy);
			
		 	var town =  "<option value=''>未選択</option>";
			for(var i = 0; i < data.length; i++){
				town += "<option value='"+data[i].town_id+"'>"+data[i].town_name+"</option>";
			}
			$("#address2").append(town);
		}
	});
}

function getimagecode(){
	$.ajax({
		url : '/base/getimagecode',
		success : function(data) {
		 	var html =  "<option value=''>未選択</option>";
			for(var i = 0; i < data.length; i++){
				html += "<option value='"+data[i].IMAGEYEARSCD+"'>"+data[i].IMAGEYEARSNAME+"</option>";
			}
			$("#imageyears").append(html);
		}
	});
}

function getstatuscode(){
	$.ajax({
		url : '/base/getstatuscode',
		success : function(data) {
		 	var html = "";
			for(var i = 0; i < data.length; i++){
				html += "<label class='checkbox-inline'>";
				if(i == 0){
					html += "	<input id='imagestate' name='imagestate' type='checkbox' checked='checked' value='"+data[i].IMAGESTATECD+"' />"+data[i].IMAGESTATENAME+"";
				} else {
					html += "	<input id='imagestate' name='imagestate' type='checkbox' checked='checked' value='"+data[i].IMAGESTATECD+"' />"+data[i].IMAGESTATENAME+"";
				}
				html += "</label> ";
			}
			$("#checkStatus").append(html);
		}
	});
}