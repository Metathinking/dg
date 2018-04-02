'use strict';

    app.controller('imageController', ['$scope', 'FileUploader', function($scope, FileUploader) {
       
        $scope.imageError="";
        $scope.imageController.useWaterLogo = $scope.imageController.useWaterLogo==undefined?false:true;
        $scope.imageController.images=[];
        var uploader = $scope.imageController.uploader = new FileUploader({
            url:  context+"/uploadImage.rest?useWaterLogo="+$scope.imageController.useWaterLogo
            +"&_t=" + new Date().getTime(),
            method:"POST"
        });
        // FILTERS
        uploader.filters.push({
            name: 'customFilter',
            fn: function(item /*{File|FileLikeObject}*/, options) {
                return this.queue.length < 10;
            }
        });
        // CALLBACKS
        uploader.onWhenAddingFileFailed = function(item /*{File|FileLikeObject}*/, filter, options) {
            // console.info('onWhenAddingFileFailed', item, filter, options);
        };
        uploader.onAfterAddingFile = function(fileItem) {
            $scope.imageError="";
            var str=fileItem._file.name;
            var index=str.lastIndexOf('.');
            var strtype=str.substr(index,4);
            strtype=strtype.toLowerCase();
            if(strtype!=".png" && strtype!=".jpg" && strtype!=".gif" && strtype!=".bmp"){
                fileItem.remove();
                $scope.imageError="请上传gif, jpg, png格式的图片！";
                return;
            }
            if(fileItem._file.size/1024/1024>2){
                fileItem.remove();
                $scope.imageError="图片大小不能超过2M！";
                return;
            }
            // console.info('onAfterAddingFile', fileItem);
        };
        uploader.onAfterAddingAll = function(addedFileItems) {
            // console.info('onAfterAddingAll', addedFileItems);
        };
        uploader.onBeforeUploadItem = function(item) {
            // console.info('onBeforeUploadItem', item);
        };
        uploader.onProgressItem = function(fileItem, progress) {
            // console.info('onProgressItem', fileItem, progress);
        };
        uploader.onProgressAll = function(progress) {
            // console.info('onProgressAll', progress);
        };
        uploader.onSuccessItem = function(fileItem, response, status, headers) {
            // console.info('onSuccessItem', fileItem, response, status, headers);
            $scope.imageController.images.push(response.data);
        };
        uploader.onErrorItem = function(fileItem, response, status, headers) {
            // console.info('onErrorItem', fileItem, response, status, headers);
        };
        uploader.onCancelItem = function(fileItem, response, status, headers) {
            // console.info('onCancelItem', fileItem, response, status, headers);
        };
        uploader.onCompleteItem = function(fileItem, response, status, headers) {
            // console.info('onCompleteItem', fileItem, response, status, headers);
        };
        uploader.onCompleteAll = function() {
            // console.info('onCompleteAll');
        };

        // console.info('uploader', uploader);
    }]);