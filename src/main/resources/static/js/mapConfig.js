//地图配置文件
var mapConfig = {
    //地图坐标系
    srid : 'EPSG:4326',
    //地图边界
    bounds : [ -7711, 4288814.05250, 75740.04054, 4346765.97659 ],
    tilesOrigin : '19579.2159,4316583.8213',
    format : 'image/png',
    // wms服务器地址
    wmsUrl : wmsUrl +'/wms',
    // businessLayers的workspace(geoserver中的)
    businessLayersWorkspace: 'NTS',
    // 基础图层信息
    // mapType:
    //      tile:纯切片图层
    //      wmsTile:geoserver的wms切片图层
    //      wmsSingleTile:geoserver的wms不切片图层
    //      wmsGwc:geoserver的wms切片缓存图层
    //      其他待定
    baseLayers : [
        //{name : '影像层', layer:'YCZ:ycz', show_by_default: false, tiled: true, isGwc:true, typeName:"基础图层"},
        {name : '土地利用分类层', layer:'YCZ:base_land_use_classes',showByDefault: false, mapType: 'wmsSingleTile', typeName:"基础图层"}
    ],
    // 其他业务图层信息,目前在数据库的base_layer表中读取。
    businessLayers : []
}