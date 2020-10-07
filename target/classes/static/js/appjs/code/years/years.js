var prefix = "/code/years"

$(function () {
    load();
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
                // 
                singleSelect: false, // 
                // contentType : "application/x-www-form-urlencoded",
                // 
                pageSize: 10, // 
                pageNumber: 1, //
                //search : true, // 
                showColumns: false, // 
                sidePagination: "server", // "client" 或者 "server"
                queryParams: function (params) {
                    return {
                        limit: params.limit,
                        offset: params.offset,
                        imageyearscd: $('#imageyearscd').val(),
                        imageyearsname: $('#imageyearsname').val()
                    };
                },
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
                    {
                        field: 'imageyearscd',
                        title: 'コード'
                    },
                    {
                        field: 'imageyearsname',
                        title: '名称'
                    },
//                    {
//                        field: 'recInsTs',
//                        title: '登録日時',
//                        formatter: function (value, row, index) {
//                        	return value.substr(0,10)
//                        }
//                    },
                    {
                        title: '',
                        field: 'id',
                        align: 'center',
                        formatter: function (value, row, index) {
                            var e = '<a class="btn btn-primary btn-sm ' + s_edit_h + '" href="#" mce_href="#" title="変更" onclick="edit(\''
                                + row.imageyearscd
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
                                + row.imageyearscd
                                + '\')"><i class="fa fa-remove">削除</i></a> ';
                            return d;
                        }
                    }]
            });
}

function reLoad() {
    $('#exampleTable').bootstrapTable('refresh');
}

function add() {
    layer.open({
        type: 2,
        title: '新規登録',
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['356px', '354px'],
        content: prefix + '/add' // iframe的url
    });
}

function edit(id) {
    layer.open({
        type: 2,
        title: '変更',
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['356px', '354px'],
        content: prefix + '/edit/' + id // iframe的url
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
