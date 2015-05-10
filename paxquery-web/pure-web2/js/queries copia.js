var queries_forum = {
               "Q1": 'for $post in collection("file:///Users/jalvaro/XML/helsinki/dataset/dataset13.xhtml")/sites/html/body\n\t/div[@class="body_wrapper"]/div[@style="margin-right:170px;"]/div[@id="postlist"]/ol/li\n\t/div[@class="postdetails"],\n\t$username in $post/div/div/div/a/strong/text(),\n\t$content in $post//div[@class="postbody"]/div[@class="postrow"]/div[@class="content"]\nwhere $username = "LWaB"\nreturn <res user="{$username}">{$content}</res>',
               "Q2": 'for $post in collection("file:///Users/jalvaro/XML/helsinki/dataset/dataset13.xhtml")/sites/html/body\n\t' +
                     '/div[@class="body_wrapper"]/div[@style="margin-right:170px;"]/div[@id="postlist"]/ol/li\n\t' +
                     '/div[@class="postdetails"],\n\t' +
                     '$username in distinct-values($post/div/div/div/a/strong/text()),\n\t'+
                     '$numposts in $post/div[@class="userinfo"]/dl/dd[@id="numposts"]/text()\n'+
                     'where $numposts > 100\n'+
                     'return <res user="{$username}"></res>'
       };
       
       var queries_synthetic = {
               "Q1": 'for $p in collection("file:///Users/jalvaro/XML/helsinki/sintetic/People")//person[profile/age/text() > 30],\n\t'+ 
    '$pid in $p/@id,\n\t'+ 
    '$pname in $p/name/text()\n'+
'for $cas in collection("file:///Users/jalvaro/XML/helsinki/sintetic/ClosedAuctions")//closed_auction,\n\t'+
    '$bid in $cas//buyer/@person,\n\t'+
    '$iref in $cas//itemref/@item\n'+
'for $item in collection("file:///Users/jalvaro/XML/helsinki/sintetic/Items")//item,\n\t'+
    '$itemid in $item/@id\n'+
'where $pid = $bid and $iref = $itemid\n'+
'return <res><person id="{$pid}" name="{$pname}"></person>{$item}</res>',
               "Q2": 'for $person in collection("file:///Users/jalvaro/XML/helsinki/sintetic/People")/site//person,\n\t'+
  '$personid in $person/@id, $personname in $person/name/text()\n'+
'let $cap :=\n\t'+
  'for $cauction in collection("file:///Users/jalvaro/XML/helsinki/sintetic/ClosedAuctions")\n\t\t'+'/site//closed_auction,\n\t\t'+
  '$buyer in $cauction/buyer/@person,\n\t\t'+
  '$item in $cauction/itemref\n\t'+
  'where $personid = $buyer\n\t'+
  'return $item\n'+
'let $numitems := count($cap)\n'+
'where $numitems > 0\n'+
'return <res personid="{$personid}" name="{$personname}" numitems="{$numitems}">{$cap}</res>'
       };