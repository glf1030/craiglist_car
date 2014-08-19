
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
	var $fetchListUrl = '/car/preOwnerCar/listCars/camry/1';
	var $msg_funcNotAvail = '<spring:message code="message.func.not.available"/>';
</script>
<script type="text/template" id="tag_item_template">
		<div class="thumbnail">
			<div class="spn3-img-wrapper">
				<div><@= sellprice @> </div>
			</div>
			<p style="line-height: 0px;"></p>
			<h4><@= model @></h4>
			 <p><a href="<@= url @>" data-toggle="tab">link</a>.</p>
		</div>
</script>

<div>
	<div class="row-fluid item-list">
		<ul class="thumbnails">
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
				url:null

		}
	});

	var TagList = Backbone.Collection.extend({
		url : $fetchListUrl,
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
					url: this.model.get('url')
			};
			var template = _.template($("#tag_item_template").html(), variables);

			this.$el.html(template);

			return this;
		}
	});

	tagList = new TagList();

	var App = Backbone.View.extend({
		el : $('ul.thumbnails'),
		initialize : function() {
			this.render();
		},
		render : function() {
			this.$el.empty();
			var idx = 0;
			tagList.each(function(tag) {
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
			app = new App();
		}
	});

});

</script>
</html>