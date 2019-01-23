app.controller("baseController",function($scope){
    $scope.paginationConf = {
        currentPage:1,// 当前页号
        totalItems:0,// 总记录数
        itemsPerPage:10,// 页大小
        perPageOptions:[10, 20, 30, 40, 50],// 可选择的每页大小
        onChange: function () {// 当上述的参数发生变化了后触发
            $scope.reloadList();
        }
    };

    $scope.reloadList = function () {
        //根据分页信息查询数据
        $scope.search($scope.paginationConf.currentPage, $scope.paginationConf.itemsPerPage);
    };

    $scope.selectedIds=[];

    $scope.updateSelection=function($event,id){
        if($event.target.checked){
            $scope.selectedIds.push(id);
        }else{
            var index = $scope.selectedIds.indexOf(id);
            $scope.selectedIds.splice(index,1);
        }
    };


})