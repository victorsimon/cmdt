if (typeof jQuery !== 'undefined') {
	(function($) {
		$('#spinner').ajaxStart(function() {
			$(this).fadeIn();
		}).ajaxStop(function() {
			$(this).fadeOut();
		});
	})(jQuery);
} 

/**
 * Hide the URL address bar on standard Android's browser by setting enough
 * document height and auto scrolling to active the bar hiding feature
 */
function hideAddressBar()
{
  if(!window.location.hash)
  {
      if(document.height < window.outerHeight + 10)
      {
          document.body.style.height = (window.outerHeight + 50) + 'px';
      }
      setTimeout(function()
      {
      	window.scrollTo(0, 1);
      }, 50);
  }
}

/**
 * Start up procedure to hide the Android's URL bar
 */
window.addEventListener("load", function()
{
		hideAddressBar();
	window.addEventListener("orientationchange", hideAddressBar);
});