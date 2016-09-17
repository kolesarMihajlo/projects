var libApp=angular.module('libApp.controller',[]);


libApp.controller('authController',

		function($rootScope,$scope, $http, $location, $route,userService) {
			
			var self = this;

			self.tab = function(route) {
				return $route.current && route === $route.current.controller;
			};
			$scope.getMeAUser=function(){
				userService.getAll($rootScope.username)
					.success(function(data){
							$rootScope.NiceLitteKorisnik=data[0];
							var img=null;
							if(data[0].image){
								img=data[0].image;	
							}
							var convert="data:image/jpg;base64,"+img;
							$rootScope.NiceLitteKorisnik.image=convert;
							
					})
					.error(function(){
					});
			};

			var authenticate = function(credentials, callback) {

				var headers = credentials ? {
					authorization : "Basic "
							+ btoa(credentials.username + ":"
									+ credentials.password)
				} : {};

				

				$http.get('api/user', {
					headers : headers
				}).then(function(response) {
					if (response.data.name) {
						$scope.error=false;
						$rootScope.authenticated = true;
						$rootScope.username=response.data.name;
						if($rootScope.NiceLitteKorisnik==undefined||$rootScope.NiceLitteKorisnik.username!=response.data.name){
							$scope.getMeAUser();
						}
						$scope.getMeAUser();
						if(!response.data.nonExpired){
							$rootScope.expiredAccount=true;
						}
						//Change so that is goes thorough every element in the list and checks for everithing
						//in other no so long word you need for loop around all this user and other stuff
						for(var rol in response.data.roles){
							if(response.data.roles[rol]==="ROLE_USER" || response.data.roles[rol]==="USER"){
								$rootScope.userAuthenticated=true;
							}
							if(response.data.roles[rol]==="ROLE_ADMIN" || response.data.roles[rol]==="ADMIN"){
								$rootScope.adminAuthenticated=true;
							}
						}
						// if(response.data.roles[0]==="ROLE_USER"|| response.data.roles[0]==="USER"){
						// 	$rootScope.userAuthenticated=true;
						// }else if(response.data.roles[0]==="ROLE_ADMIN"||response.data.roles[0]==="ADMIN"){
						// 	$rootScope.adminAuthenticated=true;
						// }else{
						// 	$rootScope.userAuthenticated=false;
						// 	$rootScope.adminAuthenticated=false;
						// }
					} else {
						$rootScope.authenticated = false;
					}
					callback && callback($rootScope.authenticated);
				}, function(response) {
					$rootScope.authenticated = false;
					callback && callback(false);
					if(response.status==401){
						$scope.error=true;
					}
				});

			};

			authenticate();

			self.credentials = {};
			self.login = function() {
				authenticate(self.credentials, function(authenticated) {
					if (authenticated) {
						console.log("Login succeeded")
						$location.path("/");
						self.error = false;
						$rootScope.authenticated = true;
					} else {
						console.log("Login failed")
						$location.path("/login");
						self.error = true;
						$rootScope.authenticated = false;
					}
				})
			};

			self.logout = function() {
				$http.post('logout', {}).finally(function() {
					$rootScope.authenticated = false;
					$rootScope.userAuthenticated=false;
					$rootScope.adminAuthenticated=false;
					
					$location.path("/");
				});
			};

			
			
});

libApp.controller('UserController',function($scope,$http,$location,$routeParams,$sce,multipartForm,userService){
	$scope.imageConversion=function(img){
		var convert="data:image/jpg;base64,"+img;
		return convert;
	}

	$scope.getAll=function(){
		var nullparam=null;
		userService.getAll(nullparam,$scope.page)
				.success(function(data,status,headers){
					$scope.users=data;
					$scope.totalPages=headers('total-pages');
					$scope.totalResults=headers('total-elements');

					$scope.totalPagesArray=new Array();
					for(var i=0;i<$scope.totalPages;i++){
						
						$scope.totalPagesArray.push(i);
						
					}
					//FOR IMAGE CONVERSION, NOW THIS IS THE REAL PROBLEM WITH DB PICTURE KEEPING
					//AND NOT FILE SISTEM
					for(var i in $scope.users){
						if($scope.users[i].image){
							$scope.users[i].image=$scope.imageConversion($scope.users[i].image);
						}
					}
					if($scope.users.length==0){
						if($scope.page>1){
							$scope.page=$scope.page-1;
							$scope.getAll();
						}
					}

				})
				.error(function(data,status){

				});
		
	};
	$scope.remove=function(id){
		userService.remove(id)
			.success(function(data){
				$scope.getAll();
			})
			.error(function(){

			})
	};

	$scope.register=function(){
		multipartForm.post($scope.newUser)
			.success(function(data){
				$scope.registeredUser=data;
			})
			.error(function(){

			})
	};

	$scope.getRoles=function(){
		userService.getRoles()
			.success(function(data){
				$scope.roles=data
			})
			.error(function(){

			})
		
	};
	$scope.setPage=function(num){
			$scope.page=num;
			$scope.getAll();
		};

});

libApp.controller('BookController',function($scope,$location,$routeParams,$sce,bookService,$rootScope){

		$scope.getAll=function(){
			bookService.getAll($scope.search,$scope.page,$scope.ASCDESC,$scope.sortByParam)
				.success(function(data,status,headers){
					$scope.books=data;
					$scope.totalPages=headers('total-pages');
					$scope.totalResults=headers('total-elements');



					$scope.totalPagesArray=new Array();

					for(var i=0;i<$scope.totalPages;i++){
						
						$scope.totalPagesArray.push(i);
						
					}
					if($scope.books.length==0){
						if($scope.page>1){
							$scope.page=$scope.page-1;
							$scope.getAll();
						}
					}
				})
				.error(function(data){

				})
			
		};
		// $scope.getOne=function(){
		// 	bookService.save($scope.book.id)
		// 		.success(function(){
		// 			$location.path('/books')
		// 		})
		// 		.error(function(data,status){

		// 		})
		// };
		$scope.remove=function(id){
			bookService.remove(id)
				.success(function(){
					$scope.getAll();
				})
				.error(function(){

				})
		};
		$scope.init=function(){
			if($rootScope.book!=undefined&&$rootScope.book.id==$routeParams.id){
				var justForDiagnostic="joy";

			}else{
				$scope.book={};
				var path=$location.path();
				if(path==="/books/view/"+$routeParams.id){
					//do something
				}else if(path==="/books/edit/"+$routeParams.id){
					//do something
				}else{
					//do something more
				}
				if($routeParams.id){
					bookService.getOne($routeParams.id)
						.success(function(data){
							$scope.book=data;
						})
						.error(function(){

						})
				}
			}
		};
		$scope.setPage=function(num){
			$scope.page=num;
			$scope.getAll();
		};

		$scope.getDetails=function(book){
			$rootScope.book=book;
			$location.path('/books/view/'+book.id);
		};
		$scope.orderAscDesc=function(orderParam,atribute){
			$scope.sortByParam=atribute;
			if(orderParam){
				$scope.ASCDESC="ASC";
				$scope.getAll();
			}else{
				$scope.ASCDESC="DESC";
				$scope.getAll();
			}

		};
		$scope.view=function(id){
			$location.path('/books/view/'+id);
		}


	


});
libApp.controller('AuthorController',function($scope,$location,$routeParams,authorService){

		$scope.getAll=function(){
			authorService.getAll($scope.search,$scope.page)
				.success(function(data,status,headers){
					$scope.authors=data;
					$scope.totalPages=headers('total-pages');
					$scope.totalResults=headers('total-elements');



					$scope.totalPagesArray=new Array();

					for(var i=0;i<$scope.totalPages;i++){
						
						$scope.totalPagesArray.push(i);
						
					}
					if($scope.authors.length==0){
						if($scope.page>1){
							$scope.page=$scope.page-1;
							$scope.getAll();
						}
					}
				})
				.error(function(data){

				})
			
		};
		// $scope.getOne=function(){
		// 	bookService.save($scope.author.id)
		// 		.success(function(){
		// 			$location.path('/author')
		// 		})
		// 		.error(function(data,status){

		// 		})
		// };
		$scope.remove=function(id){
			authorService.remove(id)
				.success(function(){
					$scope.getAll();
				})
				.error(function(){

				})
		};
		$scope.init=function(){
			$scope.author={};
			var path=$location.path();
			if(path==="/authors/view/"+$routeParams.id){
				//do something
			}else if(path==="/authors/edit/"+$routeParams.id){
				//do something
			}else{
				//do something more
			}
			if($routeParams.id){
				authorService.getOne($routeParams.id)
					.success(function(data){
						$scope.author=data;
					})
					.error(function(){

					})
			}
		};
		$scope.setPage=function(num){
		$scope.page=num;
		$scope.getAll();


	};


});

libApp.directive('fileModel',function($parse){
	return {
		restrict: 'A',
		link: function(scope, element, attrs){
			var model = $parse(attrs.fileModel);
			var modelSetter = model.assign;

			element.bind('change', function(){
				scope.$apply(function(){
					modelSetter(scope, element[0].files[0]);
				})
			})
		}
	};
});

