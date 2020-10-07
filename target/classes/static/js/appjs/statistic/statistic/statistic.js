var prefix = "/statistic/statistic"
$(function () {
    load();
});

function load() {
    $('#exampleTable')
        .bootstrapTable(
            {
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
                sidePagination: "server", // "client" 或者 "server"
                queryParams: function (params) {
                    return {
                        limit: params.limit,
                        offset: params.offset
                        // name:$('#searchName').val(),
                        // username:$('#searchName').val()
                    };
                },
                // queryParamsType = 'limit' 
                // limit, offset, search, sort, order 
                // pageSize, pageNumber, searchText, sortName,
                // sortOrder.
                columns: [
                    {
                        checkbox: true
                    },
                                            {
                                                field: '',
                                                title: '序号',
                                                formatter: function (value, row, index) {
                                                    var pageSize = $('#exampleTable').bootstrapTable('getOptions').pageSize;     //
                                                    var pageNumber = $('#exampleTable').bootstrapTable('getOptions').pageNumber; //
                                                    return pageSize * (pageNumber - 1) + index + 1;    // 
                                                }
                        },
                                            {
                            field: 'type',
                            title: '类型'
                        },
                                            {
                            field: 'key',
                            title: '属性名'
                        },
                                            {
                            field: 'value',
                            title: '属性值'
                        },
                                            {
                            field: 'seq',
                            title: '排序'
                        },
                                            {
                            field: 'remark',
                            title: '备注'
                        },
                                        {
                        title: '操作',
                        field: 'id',
                        align: 'center',
                        formatter: function (value, row, index) {
                            var e = '<a class="btn btn-primary btn-sm ' + s_edit_h + '" href="#" mce_href="#" title="编辑" onclick="edit(\''
                                + row.id
                                + '\')"><i class="fa fa-edit"></i></a> ';
                            var d = '<a class="btn btn-warning btn-sm ' + s_remove_h + '" href="#" title="删除"  mce_href="#" onclick="remove(\''
                                + row.id
                                + '\')"><i class="fa fa-remove"></i></a> ';
                            var f = '<a class="btn btn-success btn-sm" href="#" title="备用"  mce_href="#" onclick="resetPwd(\''
                                + row.id
                                + '\')"><i class="fa fa-key"></i></a> ';
                            return e + d;
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
        title: '增加',
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['800px', '520px'],
        content: prefix + '/add' // iframe的url
    });
}
function edit(id) {
    layer.open({
        type: 2,
        title: '编辑',
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['800px', '520px'],
        content: prefix + '/edit/' + id // iframe的url
    });
}
function remove(id) {
    layer.confirm('确定要删除选中的记录？', {
        btn: ['确定', '取消']
    }, function () {
        $.ajax({
            url: prefix + "/remove",
            type: "post",
            data: {
                'id': id
            },
            success: function (r) {
                if (r.code == 0) {
                    layer.msg(r.msg);
                    reLoad();
                } else {
                    layer.msg(r.msg);
                }
            }
        });
    })
}

function resetPwd(id) {
}
function batchRemove() {
    var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
    if (rows.length == 0) {
        layer.msg("请选择要删除的数据");
        return;
    }
    layer.confirm("确认要删除选中的'" + rows.length + "'条数据吗?", {
        btn: ['确定', '取消']
        // 按钮
    }, function () {
        var ids = new Array();
        // 遍历所有选择的行数据，取每条数据对应的ID
        $.each(rows, function (i, row) {
            ids[i] = row['id'];
        });
        $.ajax({
            type: 'POST',
            data: {
                "ids": ids
            },
            url: prefix + '/batchRemove',
            success: function (r) {
                if (r.code == 0) {
                    layer.msg(r.msg);
                    reLoad();
                } else {
                    layer.msg(r.msg);
                }
            }
        });
    }, function () {

    });
}