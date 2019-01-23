app.service("brandService",function ($http) {

    this.findOne=function (id) {
        return $http.get("../brand/findOne.do?id="+id);
    };

    this.update=function(entity){
        return $http.post("../brand/update.do",entity);
    };

    this.add=function(entity){
        return $http.post("../brand/add.do",entity);
    };

    this.delete=function (selectedIds) {
        return $http.get("../brand/delete.do?ids="+selectedIds);
    };

    this.search=function(page,rows,searchEntity){
       return $http.post("../brand/search.do?page="+page+"&rows="+rows,searchEntity);
    };

});