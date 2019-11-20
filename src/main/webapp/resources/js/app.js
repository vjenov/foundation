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
			$(this).css({'background-color':'yellow'})
			$(this).siblings().css({'background-color' : 'white'})
			switch($(this).text()) {
			case 'naver' :
				$.getJSON(_+'/crawler/naver',d=>{
					$('#right').empty()
					$.each(d, (i,j)=>{
						$('<div/>')
						.css({width:'40%', height:'40%', border: '3px solid red', float : 'left'})
						.html('<h1>'+j.origin + '-></h1><h4>'+ j.trans + '</h4>')
						.appendTo('#right')
					})
				})
				break;
			case 'cgv' :
				$.getJSON(_+'/crawler/cgv',d=>{
					$('#right').empty()
					$.each(d, (i,j)=>{
						$('<div><img style ="width:200px;" src="'+j.photo+'"/><br/>'+j.title +'<br/>' + j.percent +'<br/>' + j.textinfo + '</div>')
						.css({border: '3px solid red', float : 'left'})
						.appendTo('#right')
					})
				})
				break;
			case 'bugs' :
				$.getJSON(_+'/crawler/bugs',d=>{
					let pager = d.pager;
					let list = d.list;
					$('#right').empty()
					$('<table id = "content"><tr id = "head"></tr></table>')
					.css({width:'100%', height:'80px', border: '3px solid yellow'})
					.appendTo('#right')
					$.each(['No.','곡명','아티스트','썸네일'], (i,j)=>{
						$('<th/>').css({width:'25%', height:'100%', border: '1px solid yellow'})
						.html('<b>'+ j +'</b>')
						.appendTo('#head')
					})
					$.each(list, (i,j)=>{
						$('<tr><td><img src="' + j.thumbnail + '"/></td><td>' + j.seq + '</td><td>' + j.title + '</td><td>' + j.artist + '</td></tr>')
						.appendTo('#content tbody')
						})
					$('#content tr td').css({border: '1px solid black'})
					for(i = pager.startPage; i <= pager.endPage; i++) {
						$('<span>'+(i+1)+'</span>')
						.css({width:'20px', height:'20px', margin: '0 auto', border: '1px solid red'})
						.appendTo('#right')
					}
				})
				break;
			}
		})})
	}
	return {run}
})()