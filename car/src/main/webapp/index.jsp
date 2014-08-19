<!DOCTYPE html>
<html>
<head>
<title>the5fire-backbone-model</title>
</head>
<body>
</body>
<script src="http://the5fireblog.b0.upaiyun.com/staticfile/jquery-1.10.2.js"></script>
<script src="http://the5fireblog.b0.upaiyun.com/staticfile/underscore.js"></script>
<script src="http://the5fireblog.b0.upaiyun.com/staticfile/backbone.js"></script>
<script type="text/javascript">
	var $fetchListUrl_camry = '${pageContext.request.contextPath}/preOwnerCar/listCars/camry/1';
	var $msg_funcNotAvail = '<spring:message code="message.func.not.available"/>';
	var $fetchListUrl_corolla = '${pageContext.request.contextPath}/preOwnerCar/listCars/corolla/1';
		var $fetchListUrl_civic = '${pageContext.request.contextPath}/preOwnerCar/listCars/civic/1';

	var $fetchListUrl_accord = '${pageContext.request.contextPath}/preOwnerCar/listCars/accord/1';

</script>
<script type="text/template" id="tag_item_template">
		<div class="thumbnail">
			<div class="spn3-img-wrapper">
				<div><@= model @> price: <@= sellprice @> link:<a href="<@= url @>" data-toggle="tab"> mileage:<@=mileage@> vin:<@=vin@></div>
			</div>
		</div>
</script>



<div>
<h4> 6000-12000 camry, corolla, civic, accord </h4>
	<div class="row-fluid item-list">
	    camry:
		<ul class="camry_0">
		</ul>
		corolla:
		<ul class="corolla_0">
		</ul>
		civic:
		<ul class="civic_0">
		</ul>
		accord:
		<ul class="accord_0">
		</ul>
	</div>
</div>





<script>
/* JS */
//@Character-set = UTF-8
//@see /rshop-cms/tags/mylist
var tagList;
var app;

$(document).ready(function() {
	_.templateSettings = {
		interpolate : /\<\@\=(.+?)\@\>/gim,
		evaluate : /\<\@([\s\S]+?)\@\>/gim,
		escape : /\<\@\-(.+?)\@\>/gim
	};

	var Tag = Backbone.Model.extend({
		defaults : {
			id : 0,
			sellprice : null,
			model : null,
				url:null,
				vin:null,
				mileage:null

		}
	});

	var TagList = Backbone.Collection.extend({
		url : $fetchListUrl_camry,
		model : Tag
	});

	var TagList_corolla = Backbone.Collection.extend({
		url : $fetchListUrl_corolla,
		model : Tag
	});

	var TagList_civic = Backbone.Collection.extend({
		url : $fetchListUrl_civic,
		model : Tag
	});

			var TagList_accord = Backbone.Collection.extend({
		url : $fetchListUrl_accord,
		model : Tag
	});



	var TagView = Backbone.View.extend({
		tagName : 'li',
		className : 'span3',
		initialize : function() {
			// this.listenTo(this.model, 'change', this.render);
		},
		render : function() {
			var variables = {
				id : this.model.get('id'),
				sellprice : this.model.get('sellprice'),
				model : this.model.get('model'),
					url: this.model.get('url'),
					vin:this.model.get('vin'),
					mileage:this.model.get('mileage')
			};
			var template = _.template($("#tag_item_template").html(), variables);

			this.$el.html(template);

			return this;
		}
	});


	var TagView_corolla = Backbone.View.extend({
		tagName : 'li',
		className : 'span3',
		initialize : function() {
			// this.listenTo(this.model, 'change', this.render);
		},
		render : function() {
			var variables = {
				id : this.model.get('id'),
				sellprice : this.model.get('sellprice'),
				model : this.model.get('model'),
					url: this.model.get('url'),
					vin:this.model.get('vin'),
					mileage:this.model.get('mileage')
			};
			var template = _.template($("#tag_item_template").html(), variables);

			this.$el.html(template);

			return this;
		}
	});

	var TagView_civic = Backbone.View.extend({
		tagName : 'li',
		className : 'span3',
		initialize : function() {
			// this.listenTo(this.model, 'change', this.render);
		},
		render : function() {
			var variables = {
				id : this.model.get('id'),
				sellprice : this.model.get('sellprice'),
				model : this.model.get('model'),
					url: this.model.get('url'),
					vin:this.model.get('vin'),
					mileage:this.model.get('mileage')
			};
			var template = _.template($("#tag_item_template").html(), variables);

			this.$el.html(template);

			return this;
		}
	});
		var TagView_accord = Backbone.View.extend({
		tagName : 'li',
		className : 'span3',
		initialize : function() {
			// this.listenTo(this.model, 'change', this.render);
		},
		render : function() {
			var variables = {
				id : this.model.get('id'),
				sellprice : this.model.get('sellprice'),
				model : this.model.get('model'),
					url: this.model.get('url'),
					vin:this.model.get('vin'),
					mileage:this.model.get('mileage')
			};
			var template = _.template($("#tag_item_template").html(), variables);

			this.$el.html(template);

			return this;
		}
	});





	tagList = new TagList();

	tagList_corolla=new TagList_corolla();

	tagList_civic=new TagList_civic();

	tagList_accord=new TagList_accord();

	var App_camry = Backbone.View.extend({
		el : $('ul.camry_0'),
		initialize : function() {
			this.render();
		},
		render : function() {
			this.$el.empty();
			var idx = 0;
			tagList.each(function(tag) {
				var view = new TagView_corolla({
					model : tag
				});
				var liEl = view.render().el;
				if (idx % 4 == 0) {
					$(liEl).css('margin-left', '0px');
				}
				idx++;
				this.$el.append(liEl);
			}, this);

			// this.$el.find('ul.thumbnails img').lazyLoadXT();

			return this;
		}
	});


	var App_corolla = Backbone.View.extend({
		el : $('ul.corolla_0'),
		initialize : function() {
			this.render();
		},
		render : function() {
			this.$el.empty();
			var idx = 0;
			tagList_corolla.each(function(tag) {
				var view = new TagView({
					model : tag
				});
				var liEl = view.render().el;
				if (idx % 4 == 0) {
					$(liEl).css('margin-left', '0px');
				}
				idx++;
				this.$el.append(liEl);
			}, this);

			// this.$el.find('ul.thumbnails img').lazyLoadXT();

			return this;
		}
	});

		var App_civic = Backbone.View.extend({
		el : $('ul.civic_0'),
		initialize : function() {
			this.render();
		},
		render : function() {
			this.$el.empty();
			var idx = 0;
			tagList_civic.each(function(tag) {
				var view = new TagView({
					model : tag
				});
				var liEl = view.render().el;
				if (idx % 4 == 0) {
					$(liEl).css('margin-left', '0px');
				}
				idx++;
				this.$el.append(liEl);
			}, this);

			// this.$el.find('ul.thumbnails img').lazyLoadXT();

			return this;
		}
	});

		var App_accord = Backbone.View.extend({
		el : $('ul.accord_0'),
		initialize : function() {
			this.render();
		},
		render : function() {
			this.$el.empty();
			var idx = 0;
			tagList_accord.each(function(tag) {
				var view = new TagView({
					model : tag
				});
				var liEl = view.render().el;
				if (idx % 4 == 0) {
					$(liEl).css('margin-left', '0px');
				}
				idx++;
				this.$el.append(liEl);
			}, this);

			// this.$el.find('ul.thumbnails img').lazyLoadXT();

			return this;
		}
	});

	tagList.fetch({
		success : function(tagList) {
			// Because fetch() is asynchronous, should not initialize App until list is fetched.
			// alert("fetch succ");
			app = new App_camry();
		}
	});

	tagList_corolla.fetch
    ({
		success : function(tagList_corolla) {
			// Because fetch() is asynchronous, should not initialize App until list is fetched.
			// alert("fetch succ");
			app = new App_corolla();
		}
	});

		tagList_civic.fetch
    ({
		success : function(tagList_civic) {
			// Because fetch() is asynchronous, should not initialize App until list is fetched.
			// alert("fetch succ");
			app = new App_civic();
		}
	});

		tagList_accord.fetch
    ({
		success : function(tagList_accord) {
			// Because fetch() is asynchronous, should not initialize App until list is fetched.
			// alert("fetch succ");
			app = new App_accord();
		}
	});
});

</script>
</html>