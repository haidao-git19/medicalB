//Tab
$(document).ready(function(){
    initTab();
    function initTab(){
	var J_tab = $('.J_tab'), len = J_tab.length, i;
	for(i=0; i<len-1; i++){
	    setTab(J_tab[i],"menu-active");
	}
	setTab(J_tab[len-1],"menu-active");
    }
    function setTab(root,activeClass){
	var menu = $(root).find('.J_menu').children(),content = $(root).find('.J_content').children();
	menu.mouseenter(function(){
	    tabAction($(this),content,activeClass)
	})
    }
    function tabAction(titleCell,content,activeClass){
	var index = titleCell.index();
	content.eq(index).show().siblings().hide();
	titleCell.addClass(activeClass).siblings().removeClass(activeClass);
    }
    
    
    
})

