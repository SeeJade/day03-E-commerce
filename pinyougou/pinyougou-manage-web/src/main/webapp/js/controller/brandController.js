app.controller("brandController", function ($scope,$controller,brandService) {


    $controller("baseController",{$scope:$scope});


    $scope.searchEntity={};

    $scope.search=function(page,rows){
        brandService.search(page,rows,$scope.searchEntity).success(function(responce){
            $scope.list=responce.rows;
            $scope.paginationConf.totalItems=responce.total;
        })
    };


    $scope.findOne=function (id) {
        brandService.findOne(id).success(function(responce){
            $scope.entity=responce;
        })
    };

    $scope.save=function(){
        var obj;
        if($scope.entity.id!=null){
            obj = brandService.update(entity);
        }else{
            obj = brandService.add(entity);
        }
        obj.success(function (resopnce) {
            if(resopnce.success){
                $scope.reloadList();
            }else {
                alert(resopnce.message);
            }
        })
    };




    $scope.delete=function () {
        if(!$scope.selectedIds.length>0){
            alert("请选择再删除");
            return;
        }
        if(confirm("确定要删除数据吗？")){
            brandService.delete($scope.selectedIds).success(function(responce){
                if(responce.success){
                    $scope.reloadList();
                    $scope.selectedIds=[];
                }else{
                    alert(responce.message);
                }
            })
        }
    };

});