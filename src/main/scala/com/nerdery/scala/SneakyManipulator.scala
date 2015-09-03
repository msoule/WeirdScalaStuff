package com.nerdery.scala

import scala.collection.mutable.Queue
import scala.collection.mutable.ArrayBuffer
import scala.scalajs.js.JSApp
import org.scalajs.jquery.jQuery

import org.scalajs.dom

/**
 * Created by Mark Soule on 9/1/2015.
 */
object SneakyManipulator extends JSApp{

  // Constants
  val MAGIC_NUMBER = 2000
  val HEAD = "\n        <!-- META -->\n        <meta charset=\"utf-8\" />\n        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />\n\n        <!-- SEO -->\n        <title>JVM Newsletter &#8211; Issue #18 | JVM</title>\n        <meta name=\"description\" content=\"\" />\n        <meta name=\"author\" content=\"\" />\n        <meta name=\"robots\" content=\"noindex,nofollow,noarchive\" />\n\n        <!-- ICONS -->\n        <link rel=\"shortcut icon\" type=\"image/ico\" href=\"https://jvm.nerderylabs.com/content/themes/committee/assets/images/favicon.ico\" />\n        <link rel=\"apple-touch-icon\" href=\"https://jvm.nerderylabs.com/content/themes/committee/assets/images/apple-touch-icon.png\" />\n\n        <link rel=\"alternate\" type=\"application/rss+xml\" title=\"JVM &raquo; JVM Newsletter &#8211; Issue #18 Comments Feed\" href=\"https://jvm.nerderylabs.com/2015/09/01/jvm-newsletter-issue-18/feed/\" />\n\t\t<script type=\"text/javascript\">\n\t\t\twindow._wpemojiSettings = {\"baseUrl\":\"https:\\/\\/s.w.org\\/images\\/core\\/emoji\\/72x72\\/\",\"ext\":\".png\",\"source\":{\"concatemoji\":\"https:\\/\\/jvm.nerderylabs.com\\/wp-includes\\/js\\/wp-emoji-release.min.js?ver=4.2.1\"}};\n\t\t\t!function(a,b,c){function d(a){var c=b.createElement(\"canvas\"),d=c.getContext&&c.getContext(\"2d\");return d&&d.fillText?(d.textBaseline=\"top\",d.font=\"600 32px Arial\",\"flag\"===a?(d.fillText(String.fromCharCode(55356,56812,55356,56807),0,0),c.toDataURL().length>3e3):(d.fillText(String.fromCharCode(55357,56835),0,0),0!==d.getImageData(16,16,1,1).data[0])):!1}function e(a){var c=b.createElement(\"script\");c.src=a,c.type=\"text/javascript\",b.getElementsByTagName(\"head\")[0].appendChild(c)}var f;c.supports={simple:d(\"simple\"),flag:d(\"flag\")},c.supports.simple&&c.supports.flag||(f=c.source||{},f.concatemoji?e(f.concatemoji):f.wpemoji&&f.twemoji&&(e(f.twemoji),e(f.wpemoji)))}(window,document,window._wpemojiSettings);\n\t\t</script>\n\t\t<style type=\"text/css\">\nimg.wp-smiley,\nimg.emoji {\n\tdisplay: inline !important;\n\tborder: none !important;\n\tbox-shadow: none !important;\n\theight: 1em !important;\n\twidth: 1em !important;\n\tmargin: 0 .07em !important;\n\tvertical-align: -0.1em !important;\n\tbackground: none !important;\n\tpadding: 0 !important;\n}\n</style>\n<link rel='stylesheet' id='committees-screen-css'  href='https://jvm.nerderylabs.com/content/themes/committee/assets/styles/modern.css?ver=2.1' type='text/css' media='all' />\n<!--[if lte IE 8]>\n<link rel='stylesheet' id='committees-ie8-css'  href='https://jvm.nerderylabs.com/content/themes/committee/assets/styles/legacy.css?ver=2.1' type='text/css' media='all' />\n<![endif]-->\n<link rel='stylesheet' id='committees-highlight-css'  href='https://jvm.nerderylabs.com/content/themes/committee/assets/scripts/libs/highlight/styles/github.css?ver=2.1' type='text/css' media='all' />\n<script type='text/javascript' src='https://jvm.nerderylabs.com/content/themes/committee/assets/scripts/libs/modernizr.custom.min.js?ver=4.2.1'></script>\n<script type='text/javascript' src='https://jvm.nerderylabs.com/wp-includes/js/jquery/jquery.js?ver=1.11.2'></script>\n<script type='text/javascript' src='https://jvm.nerderylabs.com/wp-includes/js/jquery/jquery-migrate.min.js?ver=1.2.1'></script>\n<link rel='prev' title='JVM Newsletter &#8211; Issue #17' href='https://jvm.nerderylabs.com/2015/08/24/jvm-newsletter-issue-17/' />\n<link rel='canonical' href='https://jvm.nerderylabs.com/2015/09/01/jvm-newsletter-issue-18/' />\n<link rel='shortlink' href='https://jvm.nerderylabs.com/?p=859' />"
  val START_BODY = "        <div class=\"wrapper\">\n\n            <div class=\"masthead\" role=\"banner\">\n                <div class=\"masthead-logo\">\n                    <a href=\"https://jvm.nerderylabs.com\">\n                        JVM                    </a>\n                </div><!-- // END masthead-logo -->\n                <div class=\"masthead-nav\">\n                        <div class=\"navOuter\" id=\"js-nav\">\n    <ul class=\"nav\" role=\"navigation\">\n\n<li><a href='https://jvm.nerderylabs.com/'>Home</a></li>\n\n<li><a href='https://jvm.nerderylabs.com/java-code-standards/'>Java Code Standards</a><ul class=\"nav-item-subnav\">\n<li><a href='https://jvm.nerderylabs.com/automatic-code-quality-checks/'>Automatic Code Quality Checks</a></li>\n\n<li><a href='https://jvm.nerderylabs.com/build-standards/'>Build Standards</a></li>\n\n<li><a href='https://jvm.nerderylabs.com/gradle-resources/'>Gradle Resources</a></li>\n\n<li><a href='https://jvm.nerderylabs.com/sonatype-nexus-resources/'>Sonatype Nexus Resources</a></li>\n</ul>\n</li>\n\n<li><a href='https://jvm.nerderylabs.com/scala/'>Scala</a><ul class=\"nav-item-subnav\">\n<li><a href='https://jvm.nerderylabs.com/scala-code-standards/'>Scala Style Guidelines</a></li>\n\n<li><a href='https://jvm.nerderylabs.com/scala-resources/'>Scala Resources</a></li>\n\n<li><a href='https://jvm.nerderylabs.com/scala-language-highlights/'>Scala Language Highlights</a></li>\n</ul>\n</li>\n\n<li><a href='https://jvm.nerderylabs.com/java-code-standards/groovy/'>Groovy</a></li>\n\n<li><a href='https://jvm.nerderylabs.com/clojure/'>Clojure</a></li>\n\n<li><a href='/library'>Tools &amp; Libraries</a><ul class=\"nav-item-subnav\">\n<li><a href='https://jvm.nerderylabs.com/recommended-tools-for-development/'>Recommended Tools for Development</a></li>\n\n<li><a href='https://jvm.nerderylabs.com/integrating-bamboo-with-a-jvm-project/'>Integrating Bamboo with a JVM Project</a></li>\n\n<li><a href='https://jvm.nerderylabs.com/jvm-performance-profiling-tools/'>JVM Performance Profiling Tools</a></li>\n\n<li><a href='https://jvm.nerderylabs.com/integrating-bamboo-with-a-jvm-project/'>Integrating Bamboo with a JVM Project</a></li>\n\n<li><a href='https://jvm.nerderylabs.com/jrebel-recommendation/'>JRebel Recommendation</a></li>\n\n<li><a href='https://jvm.nerderylabs.com/java-8-readiness/'>Java 8 Readiness</a></li>\n</ul>\n</li>\n\n<li><a href='https://jvm.nerderylabs.com/java-resources/'>Java Resources</a></li>\n\n<li><a href='https://jvm.nerderylabs.com/cms-options/'>CMS Options</a></li>\n    </ul>\n</div>\n                </div><!-- // END masthead-nav -->\n                <div class=\"masthead-search\">\n                    <form method=\"get\" action=\"https://jvm.nerderylabs.com/\">\n    <label class=\"isVisuallyHidden\" for=\"searchTerm\">Search</label>\n    <input class=\"field field_txt field_txt_search\" type=\"text\" value=\"\" placeholder=\"Quick Search\" id=\"searchTerm\" name=\"s\" />\n    <input class=\"isVisuallyHidden\" type=\"submit\" value=\"Go\" />\n</form>                </div><!-- // END masthead-search -->\n            </div> <!-- // END masthead -->\n            <div class=\"content\" role=\"main\">\n                <div class=\"contentPanel\">\n                    <div class=\"grid\">\n                        <div class=\"grid-col grid-col_size7of10\">\n\n                                <div class=\"featuredImage\">\n            </div>\n    <div class=\"pageTitle\">\n        <div class=\"pageTitle-hd\">\n            <h1 class=\"hdg mix-hdg_xxxl\">JVM Newsletter &#8211; Issue #18</h1>\n        </div>\n        <div class=\"pageTitle-subhd\">\n            Posted September 1, 2015        </div>\n    </div> <!-- // END pageTitle -->\n\n    <div class=\"section\">\n        <div class=\"userContent\">"
  val END_BODY = "<script type='text/javascript' src='https://jvm.nerderylabs.com/content/themes/committee/assets/scripts/libs/tinyNav/tinynav.min.js?ver=2.1'></script>\n<script type='text/javascript' src='https://jvm.nerderylabs.com/content/themes/committee/assets/scripts/libs/zeroclipboard.min.js?ver=2.1'></script>\n<script type='text/javascript' src='https://jvm.nerderylabs.com/content/themes/committee/assets/scripts/libs/Transition.js?ver=2.1'></script>\n<script type='text/javascript' src='https://jvm.nerderylabs.com/content/themes/committee/assets/scripts/views/Disclosure.js?ver=2.1'></script>\n<script type='text/javascript' src='https://jvm.nerderylabs.com/content/themes/committee/assets/scripts/global.js?ver=2.1'></script>\n<script type='text/javascript' src='https://jvm.nerderylabs.com/content/themes/committee/assets/scripts/libs/highlight/highlight.pack.js?ver=2.1'></script>\n<script type='text/javascript' src='https://jvm.nerderylabs.com/wp-includes/js/comment-reply.min.js?ver=4.2.1'></script>\n        <!-- HIGHLIGHT JS -->\n        <script type=\"text/javascript\">\n            (function(){\n                hljs.tabReplace = '    ';\n                hljs.initHighlightingOnLoad();\n            })();\n        </script>"
  val END_WRAPPER = "<p><img src=\"https://jvm.nerderylabs.com/content/uploads/sites/16/2015/09/trolls.gif\" alt=\"Frozen Trolls\" /></p>\n        </div>\n    </div>\n\n    <div class=\"section\">\n                <div class=\"blurb\">\n            <div class=\"blurb-hd\">\n                <h2 class=\"hdg mix-hdg_xl\">About the Author</h2>\n            </div>\n            <div class=\"blurb-bd\">\n                <div class=\"media\">\n                    <div class=\"media-img\">\n                        <img class=\"frame\" src=\"https://mainframe.nerdery.com/profile_pics/jklun.jpg\" alt=\"Josh Klun\" />\n                    </div>\n                    <div class=\"media-bd\">\n                        <div class=\"feature-hd\">\n                            <h3 class=\"hdg mix-hdg_lg\">\n                                <a href=\"https://jvm.nerderylabs.com/author/jklun/\">Josh Klun</a>\n                            </h3>\n                        </div>\n                        <div class=\"feature-bd\">\n                            <div class=\"userContent\">\n                                JVM Technology Manager                            </div>\n                        </div>\n                    </div>\n                </div>\n            </div>\n        </div>    </div>\n\n    <div class=\"section\">\n                                                                <div class=\"comments\">\n                                <div class=\"comments-list\">\n                                    <div class=\"comments-list-hd\">\n                                                                                    <h2 class=\"hdg mix-hdg_xl\">0 Comments</h2>\n                                                                            </div>\n                                    <div class=\"comments-list-bd\">\n                                        <ol class=\"vList vlist_zebra\">\n                                            \t\t\t\t\t\t<div id=\"respond\" class=\"comment-respond\">\n\t\t\t\t<h3 id=\"reply-title\" class=\"comment-reply-title\"> <small><a rel=\"nofollow\" id=\"cancel-comment-reply-link\" href=\"/2015/09/01/jvm-newsletter-issue-18/#respond\" style=\"display:none;\">Cancel Reply</a></small></h3>\n\t\t\t\t\t\t\t\t\t<form action=\"https://jvm.nerderylabs.com/wp-comments-post.php\" method=\"post\" id=\"commentform\" class=\"comment-form\">\n\t\t\t\t\t\t<h2 class=\"hdg mix-hdg_xl\">Leave a Reply</h2>\t\t\t\t\t\t\t\t\t\t\t\t\t<p class=\"comment-notes\">Your email address will not be published.</p>\t\t\t\t\t\t\t<div class=\"chunk comment-form-author\"><div class=\"chunk-hd\"><label for=\"author\">Name</label></div><div class=\"chunk-bd\"> <input id=\"author\" class=\"field field_txt\" name=\"author\" type=\"text\" value=\"\" size=\"30\" /></div></div>\n<div class=\"chunk comment-form-email\"><div class=\"chunk-hd\"><label for=\"email\">Email</label></div><div class=\"chunk-bd\"> <input id=\"email\" class=\"field field_txt\" name=\"email\" type=\"text\" value=\"\" size=\"30\" /></div></div>\n<div class=\"chunk comment-form-url\"><div class=\"chunk-hd\"><label for=\"url\">Website</label></div><div class=\"chunk-bd\"><input id=\"url\" class=\"field field_txt\" name=\"url\" type=\"text\" value=\"\" size=\"30\" /></div></div>\n\t\t\t\t\t\t\t\t\t\t\t\t<div class=\"chunk comment-form-comment\"><div class=\"chunk-hd\"><label for=\"comment\">Comment</label></div><div class=\"chunk-bd\"><textarea class=\"field field_textarea\" id=\"comment\" name=\"comment\" cols=\"45\" rows=\"8\" aria-required=\"true\"></textarea></div></div>\t\t\t\t\t\t\n\t\t\t\t\t\t<p class=\"form-submit\"><input name=\"submit\" type=\"submit\" id=\"submit\" class=\"submit btn\" value=\"Post Comment\" /> <input type='hidden' name='comment_post_ID' value='859' id='comment_post_ID' />\n<input type='hidden' name='comment_parent' id='comment_parent' value='0' />\n</p>\t\t\t\t\t</form>\n\t\t\t\t\t\t\t</div><!-- #respond -->\n\t\t\t                                        </ol> <!-- // END vList -->\n                                                                            </div>\n                                </div>\n                            </div>\n    </div>\n\n\n                        </div> <!-- // END grid-col -->\n                        <div class=\"grid-col grid-col_size3of10\">\n\n                            <h1 class=\"isVisuallyHidden\">Related Content</h1>\n\n                            \n                        </div> <!-- // END grid-col -->\n                    </div> <!-- // END grid -->\n                </div> <!-- // END contentPanel -->\n            </div> <!-- // END content -->\n\n\t\t\t\n            <div class=\"cankle\">\n                <h1 class=\"isVisuallyHidden\">Committee Site Information</h1>\n\n                <div class=\"cankle-sites\">\n                    <ul class=\"hlist\">\n                        <li><a href=\"//android.nerderylabs.com\"><i class=\"icnCommittee icnCommittee_android\">Android</i></a></li>\n                        <li><a href=\"//craftcms.nerderylabs.com\"><i class=\"icnCommittee icnCommittee_craft\">Craft</i></a></li>\n                        <li><a href=\"//drupal.nerderylabs.com\"><i class=\"icnCommittee icnCommittee_drupal\">Drupal</i></a></li>\n                        <li><a href=\"//eehelp.nerderylabs.com\"><i class=\"icnCommittee icnCommittee_expressionengine\">Expression Engine</i></a></li>\n                        <li><a href=\"//facebook.nerderylabs.com\"><i class=\"icnCommittee icnCommittee_facebook\">Facebook</i></a></li>\n                        <li><a href=\"//fed.nerderylabs.com\"><i class=\"icnCommittee icnCommittee_frontend\">Front-End</i></a></li>\n                        <li><a href=\"//ios.nerderylabs.com\"><i class=\"icnCommittee icnCommittee_ios\">iOS</i></a></li>\n                        <li><a href=\"//jvm.nerderylabs.com\"><i class=\"icnCommittee icnCommittee_java\">Java</i></a></li>\n                        <li><a href=\"//js.nerderylabs.com\"><i class=\"icnCommittee icnCommittee_javascript\">Javascript</i></a></li>\n                        <li><a href=\"//dotnet.nerderylabs.com\"><i class=\"icnCommittee icnCommittee_dotnet\">.NET</i></a></li>\n                        <li><a href=\"//php.nerderylabs.com\"><i class=\"icnCommittee icnCommittee_php\">PHP</i></a></li>\n                        <li><a href=\"//python.nerderylabs.com\"><i class=\"icnCommittee icnCommittee_python\">Python</i></a></li>\n                        <li><a href=\"//ruby.nerderylabs.com\"><i class=\"icnCommittee icnCommittee_ruby\">Ruby</i></a></li>\n                        <li><a href=\"//nerdpress.nerderylabs.com\"><i class=\"icnCommittee icnCommittee_wordpress\">WordPress</i></a></li>\n                    </ul>\n                </div>\n\n            </div>\n            <div class=\"footer\" role=\"contentinfo\">\n                <div class=\"footer-alpha\">\n                    <ul class=\"hlist hlist_nav\">\n                        <li><a href=\"https://jvm.nerderylabs.com/wp-login.php?redirect_to=%2F\">Log in</a></li>\n                        <li><a href=\"#\" id=\"js-issue-collector\">Report an Issue</a></li>\n                    </ul>\n                </div>\n                <div class=\"footer-omega\">\n                    <small>&copy; 2012-2015</small>. Nerdery Committees.\n                </div>\n            </div> <!-- // END footer -->\n\n        </div> <!-- // END wrapper -->"
  val LINKS = "<ul>\n<li><a target=\"_blank\" href=\"http://owenrh.me.uk/blog/2015/08/31/\">Real-time development. It&#8217;s here. Now.</a></li>\n<li><a target=\"_blank\" href=\"https://www.lucidchart.com/techblog/2015/08/31/the-worst-mistake-of-computer-science/\">The worst mistake of computer science</a></li>\n<li><a target=\"_blank\" href=\"http://martinfowler.com/bliki/AntiPattern.html\">AntiPattern</a></li>\n</ul>"
  val LINKS2 = "<a href=\"http://www.scala-js.org/\" target=\"_blank\">Scala.js</a>, <a href=\"https://github.com/clojure/clojurescript\" target=\"_blank\">ClojureScript</a>, or <a href=\"http://www.gwtproject.org/\" target=\"_blank\">GWT</a>"
  val H2_START = "<h2>"
  val H2_END = "</h2>"
  val H3_START = "<h3>"
  val H3_END = "</h3>"
  val EM_START = "<em>"
  val EM_END = "</em>"
  val UL_START = "<ul>"
  val UL_END = "</ul>"
  val LI_START = "<li>"
  val LI_END = "</li>"
  val PRE_START = "<pre>"
  val PRE_END = "</pre>"
  val P_START = "<p>"
  val P_END = "</p>"
  val JS = "\t<!-- Include JavaScript dependencies -->\n\t<script type=\"text/javascript\" src=\"./target/scala-2.11/scala-js-tutorial-jsdeps.js\"></script>  \n    <!-- Include Scala.js compiled code -->\n    <script type=\"text/javascript\" src=\"./target/scala-2.11/scala-js-tutorial-fastopt.js\"></script>\n\t<!-- Run JSApp -->\n\t<script type=\"text/javascript\" src=\"./target/scala-2.11/scala-js-tutorial-launcher.js\"></script>"

  // Actual user content that will be manipulated by this app.
  var contentArray = new ArrayBuffer[String]()
  val TITLE1 = "public void developerLoop() throws ImportantUpdatesException"
  contentArray += "Stephen Hopper is giving a talk, "
  contentArray += "Bloody Simple Parallelism with Scala"
  contentArray += ", for a recruiting event at The Walker on Tuesday, September 1st. It's guaranteed to be great, but you can still wish him good luck if you see him in the cafeteria."
  val TITLE2 = "Committee"
  contentArray += "The Reactive project has been reviewed and is almost ready to go live."
  contentArray += "Team Nye Impossible has an approved project to investigate Spring XD."
  contentArray += "Mark Soule has submitted a proposal to investigate Structr, and may begin work soon."
  contentArray += "The committee is considering a researching Spring Cloud and Netflix OSS Microservices. Think you have what it takes to tackle this cutting edge project? If so, submit a brief proposal, and within a few days you could be on your way."
  val TITLE3 = "Good Reads"
  val TITLE4 = "Challenged Accepted"
  contentArray += "Every week, I'll include a small program or puzzle to implement. This is totally optional and intended to be a fun way to learn, not a burden. The best submission (as determined by capriciously, by me) will be honored with a mention and an animated gif in your honor in the next newsletter."
  contentArray += "This week, it's important to remember that JavaScript is just Java, but you know, with Script. Alright, for anybody outside of development who happens upon this page, it's really important to note: That last line is not true. Regardless, we are stepping outside the comfortable confines of the JVM this week. Instead, we're going to venture out into browser-land. The challenge for this week: Insert a script written with a transpiled JVM language (e.g. "
  contentArray += ") into a copy of this page, and make it do something crazy."
  val TITLE5 = "Last week's winner is..."
  contentArray += "Nick Russell! Nick's win is based on his use of the double-troll: Not only did he exclusively use anonymous functions with single-character parameters, he also submitted a \"Clojure\" solution that was really just Scheme. His use of the double-troll is an excellent example of the kind of code we'd never pawn off on a client, but that seems oddly at home in a code challenge. I told you these would be determined capriciously."
  contentArray += "(defn pytrips [t]\n  \"Finds a primitive Pythagorean triple (a, b, c) whose numbers add up to t\n  (pytrips 1000) -&gt; [375 200 425]\n  Note that this uses an algorithm that will only find primitive Pythagorean triples\n  Also note that the fixed-point combinator was just 'for funsies'\"\n  (((fn [f]\n      ((fn [x]\n         (f (fn [&amp; y] (apply (x x) y))))\n        (fn [x]\n          (f (fn [&amp; y] (apply (x x) y))))))\n     (fn [f]\n       (fn [n m]\n         (when (&lt;= m t)\n           (let [a (- (* m m) (* n n))\n                 b (* 2 m n)\n                 c (+ (* m m) (* n n))]\n             (if (and (= (+ a b c) t) (= (+ (* a a) (* b b)) (* c c)))\n               [a b c]\n               (if (&lt; n (- m 1))\n                 (recur (+ n 1) m)\n                 (recur 1 (+ m 1))))))))) 1 2))\n\n(let [pytrip (pytrips 1000)\n      product (if (nil? pytrip) nil (reduce * pytrip))]\n  (when (not (nil? product)) (println (first pytrip) &quot;*&quot; (second pytrip) &quot;*&quot; (nth pytrip 2) &quot;=&quot; product))\n  product)"
  contentArray += "To celebrate his win, Nick should enjoy this GIF of two trolls convincing Anna that Kristoff is just a bit of a fixer upper."

  // Sneaky sneaky stuff here
  var switchBackArray = new ArrayBuffer[(Int, Int, Int)]()

  // Build the body of the html.
  def buildBody(): String = {
    val body = START_BODY +
               // The headings make to too obvious.
               H2_START + TITLE1 + H2_END +
               P_START + contentArray(0) +
               EM_START + contentArray(1) + EM_END +
               contentArray(2) + P_END +
               H2_START + TITLE2 + H2_END +
               UL_START +
               LI_START + contentArray(3) + LI_END +
               LI_START + contentArray(4) + LI_END +
               LI_START + contentArray(5) + LI_END +
               LI_START + contentArray(6) + LI_END +
               H2_START + TITLE3 + H2_END +
               LINKS +
               H2_START + TITLE4 + H2_END +
               P_START + contentArray(7) + P_END +
               P_START + contentArray(8) +
               LINKS2 +
               contentArray(9) + P_END +
               H3_START + TITLE5 + H3_END +
               P_START + contentArray(10) + P_END +
               PRE_START + contentArray(11) + PRE_END +
               P_START + contentArray(12) + P_END +
               END_WRAPPER //+ END_BODY

    body
  }

  // Setup the head with JQuery
  def setupHead(): Unit = {
    jQuery("head").append(HEAD)
  }

  // Setup the body with JQuery and setup the interval for sneakySneaky.
  def setupBody(): Unit = {
    jQuery("body").empty().append(buildBody())
    dom.setInterval(() => sneakySneaky(), MAGIC_NUMBER)
  }

  // This... you don't want to know about this.
  def sneakySneaky(): Unit = {
    val indexMaker = scala.util.Random

    // Make it slightly more likely to switch characters
    val resetChars = indexMaker.nextInt(100)
    if(resetChars < 48) {
      switchBack(indexMaker)
    }
    else {
      switch(indexMaker)
    }
  }

  // Switch characters back using the switchBackArray.
  def switchBack(indexMaker: scala.util.Random): Unit = {
    val switchBack = switchBackArray(indexMaker.nextInt(switchBackArray.length))
    contentArray(switchBack._1) = swapChars(contentArray(switchBack._1),
      switchBack._2,
      switchBack._3)
  }

  // Switch characters and record the switch on the array.
  def switch(indexMaker: scala.util.Random): Unit = {
    val contentIndex = indexMaker.nextInt(contentArray.length)
    val firstIndex = indexMaker.nextInt(contentArray(contentIndex).length)
    val secondIndex = indexMaker.nextInt(contentArray(contentIndex).length)
    contentArray(contentIndex) = swapChars(contentArray(contentIndex),
      firstIndex,
      secondIndex)
    switchBackArray += new Tuple3(contentIndex, secondIndex, firstIndex)
    jQuery("body").empty().append(buildBody())
  }

  // Swap characters and build a new string.
  def swapChars(target: String, firstIndex: Int, secondIndex: Int): String = {
    val tempArr = target.toCharArray()
    val tempChar = tempArr(firstIndex)
    tempArr(firstIndex) = tempArr(secondIndex)
    tempArr(secondIndex) = tempChar
    new String(tempArr)
  }

  // Kick it off.
  def main(): Unit = {
    jQuery(setupHead _)
    jQuery(setupBody _)
  }
}
