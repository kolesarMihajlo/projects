var libApp=angular.module('libApp.route',['ngRoute']);

libApp.config(function($routeProvider, $httpProvider){
	$routeProvider
		.when('/',{
			templateUrl:'/static/app/html/partial/home.html'
		})
		.when('/books',{
			templateUrl:'/static/app/html/partial/books.html',
			controller:'BookController'
		})
		.when('/books/view/:id',{
			templateUrl:'/static/app/html/partial/addEditBooks.html',
			controller:'BookController'
		})
		.when('/login',{
			templateUrl:'/static/app/html/partial/login.html',
			controller:'authController',
			controllerAs:'authC'
		})
		.when('/users',{
			templateUrl:'/static/app/html/partial/users.html',
			controller:'UserController'
		})
		.when('/addUser',{
			templateUrl:'static/app/html/partial/addEditUser.html',
			controller:'UserController'
		})
		// .when('/users/:id',{
		// 	templateUrl:'/static/app/html/partial/addEditUser.html',
		// 	controller:'UserController'
		// })
		.when('/authors',{
			templateUrl:'/static/app/html/partial/authors.html',
			controller:'AuthorController'
		})
		.when('/authors/view/:id',{
			templateUrl:'/static/app/html/partial/addEditAuthor.html',
			controller:'AuthorController'
		})
		.otherwise('/');
		$httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
})