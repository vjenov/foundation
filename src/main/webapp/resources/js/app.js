"use strict";
var app = app || {}
app = (()=>{
	let _,js
	let run =x=>$.getScript(x+'/resources/js/router.js',
			()=>{$.extend(new Session(x));
			init()
			onCreate()
	})
	let init =()=>{
		_ = $.ctx()
		js = $.js()
	}
	let onCreate=()=>{
		alert('탬탬')
		setContentView()
	}
	let setContentView=()=>{
/*		<style>
		#tab {
		  font-family: arial, sans-serif;
		  border-collapse: collapse;
		  width: 100%;
		}

		#tab td, th {
		  border: 1px solid #dddddd;
		  text-align: left;
		  padding: 8px;
		}

		#tab tr:nth-child(even) {
		  background-color: #dddddd;
		}
		</style>*/
		$('<table id = "tab"><tr></tr></table>>')
		.css({
			width:'80%', height:'800px', border: '1px solid black', margin: '0 auto'})
		.appendTo('#wrapper')
		$('<td/>', {id : 'left'}).appendTo('tr')
		.css({
			width:'20%', height:'100%', border: '1px solid black', 'vertical-align':'top'
		})
		$('<td/>', {id : 'right'}).appendTo('tr')
		.css({
			width:'80%', height:'100%', border: '1px solid black'
		})
		.appendTo('tr')
		$.each(['naver', 'cgv', 'bugs'],(i,j)=>{
			$('<div/>').text(j).css({
			width:'100%',
			height:'50px',
			border: '1px solid black'
		}).appendTo('#left').click(function(){
			alert($(this).text())
			switch($(this).text()) {
			case 'naver' :
				$.getJSON(_+'/crawl/naver',d=>{})
				break;
			case 'cgv' :
				$.getJSON(_+'/crawl/cgv',d=>{})
				break;
			case 'bugs' :
				$.getJSON(_+'/crawl/bugs',d=>{})
				break;
			}
		})})
	}
	return {run}
})()