var libApp=angular.module('libApp.service',[]);

libApp.service('bookService',function($http){
	this.url='api/books';
	this.getOne=function(id){
		return $http.get(this.url+'/'+id);
	};
	this.getAll=function(search,page,ASCDESC,sortByParam){
		sortParam=sortByParam+';'+ASCDESC;

		return $http.get(this.url,{params:{'search':search,'page':page,'sortParams':sortParam}});
	};
	this.save=function(book){
		if(book.id){
			return $http.put(this.url+'/'+book.id,book);
		}else{
			return $http.post(this.url,book);
		}
	};
	this.remove=function(id){
		return $http.delete(this.url+'/'+id);
	};
});

libApp.service('userService',function($http){
	this.url='api/users';
	this.getAll=function(username,page){
		return $http.get(this.url,{params:{'username':username,'page':page}});
	};
	this.getRoles=function(){
		return $http.get('api/roles');
	};
	this.remove=function(id){
		return $http.delete(this.url+'/'+id);
	}
	this.getOne=function(id){
		return $http.get(this.url+'/'+id);
	}
});

libApp.service('authorService',function($http){
	this.url='api/authors';
	this.getOne=function(id){
		return $http.get(this.url+'/'+id);
	};
	this.getAll=function(search,page){
			return $http.get(this.url,{params:{'search':search,'page':page}});
	};
	this.save=function(author){
		if(author.id){
			return $http.put(this.url+'/'+author.id,author);
		}else{
			return $http.post(this.url,book);
		}
	};
	this.remove=function(id){
		return $http.delete(this.url+'/'+id);
	};
})

libApp.service('multipartForm', function($http){
	this.url="api/users";
	this.post = function(data){
		var fd = new FormData();
		for(var key in data){
			fd.append(key, data[key]);
		}
		return $http.post(this.url, fd, {
			transformRequest: angular.indentity,
			headers: { 'Content-Type': undefined }
		});
	}
});