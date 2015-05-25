var queries_forum = {
               "Q1": '<xqreserved>for</xqreserved> <xqvariable>$post</xqvariable> <xqreserved>in collection</xqreserved>(<xqliteral>"helsinki/dataset"</xqliteral>)/sites/html/body<br><tab></tab>'+
                      '/div[@class=<xqliteral>"body_wrapper"</xqliteral>]/div[@style=<xqliteral>"margin-right:170px;"</xqliteral>]/div[@id=<xqliteral>"postlist"</xqliteral>]/ol/li<br><tab></tab>'+
                      '/div[@class=<xqliteral>"postdetails"</xqliteral>],<br><tab></tab>'+
                      '<xqvariable>$username</xqvariable> <xqreserved>in</xqreserved> <xqvariable>$post</xqvariable>/div/div/div/a/strong/text(),<br><tab></tab>'+
                      '<xqvariable>$content</xqvariable> <xqreserved>in</xqreserved> <xqvariable>$post</xqvariable>//div[@class=<xqliteral>"postbody"</xqliteral>]/div[@class=<xqliteral>"postrow"</xqliteral>]/div[@class=<xqliteral>"content"</xqliteral>]<br>'+
                      '<xqreserved>where</xqreserved> <xqvariable>$username</xqvariable> <xqreserved>=</xqreserved> <xqliteral>"LWaB"</xqliteral><br>'+
                      '<xqreserved>return</xqreserved> <xqxml>&lt;res user="{</xqxml><xqvariable>$username</xqvariable><xqxml>}"&gt;{</xqxml><xqvariable>$content</xqvariable><xqxml>}&lt;/res&gt;</xqxml>',
               "Q2": '<xqreserved>for</xqreserved> <xqvariable>$post</xqvariable> <xqreserved>in collection</xqreserved>(<xqliteral>"helsinki/dataset"</xqliteral>)/sites/html/body<br><tab/>' +
                     '/div[@class=<xqliteral>"body_wrapper"</xqliteral>]/div[@style=<xqliteral>"margin-right:170px;"</xqliteral>]/div[@id=<xqliteral>"postlist"</xqliteral>]/ol/li<br><tab/>' +
                     '/div[@class=<xqliteral>"postdetails"</xqliteral>],<br><tab/>' +
                     '<xqvariable>$username</xqvariable> <xqreserved>in distinct-values</xqreserved>(<xqvariable>$post</xqvariable>/div/div/div/a/strong/text()),<br><tab/>'+
                     '<xqvariable>$numposts</xqvariable> <xqreserved>in</xqreserved> <xqvariable>$post</xqvariable>/div[@class=<xqliteral>"userinfo"</xqliteral>]/dl/dd[@id=<xqliteral>"numposts"</xqliteral>]/text()<br>'+
                     '<xqreserved>where</xqreserved> <xqvariable>$numposts</xqvariable> <xqreserved> > </xqreserved> <xqliteral>100</xqliteral><br>'+
                     '<xqreserved>return</xqreserved> <xqxml>&lt;res user="{</xqxml><xqvariable>$username</xqvariable><xqxml>}"&gt;&lt;/res&gt;</xqxml>'
       };
       
       var queries_synthetic = {
               "Q1": '<xqreserved>for</xqreserved> <xqvariable>$p</xqvariable> <xqreserved>in collection</xqreserved>(<xqliteral>"helsinki/synthetic/People"</xqliteral>)//person[profile/age/text() <xqreserved> > </xqreserved> <xqliteral>30</xqliteral>],<br><tab></tab>'+ 
    '<xqvariable>$pid</xqvariable> <xqreserved>in</xqreserved> <xqvariable>$p</xqvariable>/@id,<br><tab></tab>'+ 
    '<xqvariable>$pname</xqvariable> <xqreserved>in</xqreserved> <xqvariable>$p</xqvariable>/name/text()<br>'+
'<xqreserved>for</xqreserved> <xqvariable>$cas</xqvariable> <xqreserved>in collection</xqreserved>(<xqliteral>"helsinki/synthetic/ClosedAuctions"</xqliteral>)//closed_auction,<br><tab></tab>'+
    '<xqvariable>$bid</xqvariable> <xqreserved>in</xqreserved> <xqvariable>$cas</xqvariable>//buyer/@person,<br><tab></tab>'+
    '<xqvariable>$iref</xqvariable> <xqreserved>in</xqreserved> <xqvariable>$cas</xqvariable>//itemref/@item<br>'+
'<xqreserved>for</xqreserved> <xqvariable>$item</xqvariable> <xqreserved>in collection</xqreserved>(<xqliteral>"helsinki/synthetic/Items"</xqliteral>)//item,<br><tab></tab>'+
    '$itemid <xqreserved>in</xqreserved> <xqvariable>$item</xqvariable>/@id<br>'+
'<xqreserved>where</xqreserved> <xqvariable>$pid</xqvariable> <xqreserved>=</xqreserved> <xqvariable>$bid</xqvariable> <xqreserved>and</xqreserved> <xqvariable>$iref</xqvariable> <xqreserved>=</xqreserved> <xqvariable>$itemid</xqvariable><br>'+
'<xqreserved>return</xqreserved> <xqxml>&lt;res&gt;&lt;person id="{</xqxml><xqvariable>$pid</xqvariable><xqxml>}" name="{</xqxml><xqvariable>$pname</xqvariable><xqxml>}"&gt;&lt;/person&gt;{</xqxml><xqvariable>$item</xqvariable><xqxml>}&lt;/res&gt;</xqxml>',
               "Q2": '<xqreserved>for</xqreserved> <xqvariable>$person</xqvariable> <xqreserved>in collection</xqreserved>(<xqliteral>"helsinki/synthetic/People"</xqliteral>)/site//person,<br><tab/>'+
  '<xqvariable>$personid</xqvariable> <xqreserved>in</xqreserved> <xqvariable>$person</xqvariable>/@id, <xqvariable>$personname</xqvariable> <xqreserved>in</xqreserved> <xqvariable>$person</xqvariable>/name/text()<br>'+
'<xqreserved>let</xqreserved> <xqvariable>$cap</xqvariable> <xqreserved>:=</xqreserved><br><tab/>'+
  '<xqreserved>for</xqreserved> <xqvariable>$cauction</xqvariable> <xqreserved>in collection</xqreserved>(<xqliteral>"helsinki/synthetic/ClosedAuctions"</xqliteral>)'+'/site//closed_auction,<br><tab/><tab/>'+
  '<xqvariable>$buyer</xqvariable> <xqreserved>in</xqreserved> <xqvariable>$cauction</xqvariable>/buyer/@person,<br><tab/><tab/>'+
  '<xqvariable>$item</xqvariable> <xqreserved>in</xqreserved> <xqvariable>$cauction</xqvariable>/itemref<br><tab/>'+
  '<xqreserved>where</xqreserved> <xqvariable>$personid</xqvariable> <xqreserved>=</xqreserved> <xqvariable>$buyer</xqvariable><br><tab/>'+
  '<xqreserved>return</xqreserved> <xqvariable>$item</xqvariable><br>'+
'<xqreserved>let</xqreserved> <xqvariable>$numitems</xqvariable> <xqreserved>:= count</xqreserved>(<xqvariable>$cap</xqvariable>)<br>'+
'<xqreserved>where</xqreserved> <xqvariable>$numitems</xqvariable> <xqreserved> > </xqreserved> <xqliteral>0</xqliteral><br>'+
'<xqreserved>return</xqreserved> <xqxml>&lt;res personid="{</xqxml><xqvariable>$personid</xqvariable><xqxml>}" name="{</xqxml><xqvariable>$personname</xqvariable><xqxml>}" numitems="{</xqxml><xqvariable>$numitems</xqvariable><xqxml>}"&gt;{</xqxml><xqvariable>$cap</xqvariable><xqxml>}&lt;/res&gt;</xqxml>',
              "Q3": '<xqreserved>let</xqreserved> <xqvariable>$pc</xqvariable> <xqreserved>:= collection</xqreserved>(<xqliteral>"XMarkPeople"</xqliteral>)/site<br>'+
  '<xqreserved>for</xqreserved> <xqvariable>$p</xqvariable> <xqreserved>in</xqreserved> <xqvariable>$pc</xqvariable>/people/person[@id<xqreserved>=</xqreserved><xqliteral>"person0"</xqliteral>]<br>'+
  '<xqreserved>let</xqreserved> <xqvariable>$n</xqvariable> <xqreserved>:=</xqreserved> <xqvariable>$p</xqvariable>/name/text()<br>'+
  '<xqreserved>return</xqreserved> <xqvariable>$n</xqvariable>',
              "Q4": '<xqreserved>let</xqreserved> <xqvariable>$ic</xqvariable> <xqreserved>:= collection</xqreserved>(<xqliteral>"XMarkItems"</xqliteral>)/site<br>' +
  '<xqreserved>for</xqreserved> <xqvariable>$i</xqvariable> <xqreserved>in</xqreserved> <xqvariable>$ic</xqvariable>//item, <xqvariable>$d</xqvariable> <xqreserved>in</xqreserved> <xqvariable>$i</xqvariable>/description/text/text()<br>' +
  '<xqreserved>let</xqreserved> <xqvariable>$n</xqvariable> <xqreserved>:=</xqreserved> <xqvariable>$i</xqvariable>/name/text()<br>' +
  '<xqreserved>where contains</xqreserved>(<xqvariable>$d</xqvariable>, <xqliteral>"gold"</xqliteral>)<br>' +
  '<xqreserved>return</xqreserved> <xqxml>&lt;res&gt;{</xqxml><xqvariable>$n</xqvariable><xqxml>}&lt;/res&gt;</xqxml>',
              "Q5": '<xqreserved>let</xqreserved> <xqvariable>$cc</xqvariable> <xqreserved>:= collection</xqreserved>(<xqliteral>"XMarkClosedAuctions"</xqliteral>)/site<br>' +
  '<xqreserved>let</xqreserved> <xqvariable>$p</xqvariable> <xqreserved>:=</xqreserved><br>' +
  '<tab><xqreserved>for</xqreserved> <xqvariable>$i</xqvariable> <xqreserved>in</xqreserved> <xqvariable>$cc</xqvariable>/closed_auctions/closed_auction[price/text() <xqreserved>></xqreserved> <xqliteral>40</xqliteral>]<br>' +
  '<tab><xqreserved>return</xqreserved> <xqvariable>$i</xqvariable><br>' +
  '<xqreserved>let</xqreserved> <xqvariable>$c</xqvariable> <xqreserved>:= count</xqreserved>(<xqvariable>$p</xqvariable>)<br>' +
  '<xqreserved>return</xqreserved> <xqvariable>$c</xqvariable>',
              "Q6": '<xqreserved>let</xqreserved> <xqvariable>$pc</xqvariable> <xqreserved>:= collection</xqreserved>(<xqliteral>"XMarkPeople"</xqliteral>)/site<br>' +
  '<xqreserved>for</xqreserved> <xqvariable>$i</xqvariable> <xqreserved>in distinct-values</xqreserved>(<xqvariable>$pc</xqvariable>/people/person/profile/interest/@category)<br>' +
  '<xqreserved>let</xqreserved> <xqvariable>$p</xqvariable> <xqreserved>:=</xqreserved><br>' +
  '<tab><xqreserved>for</xqreserved> <xqvariable>$t</xqvariable> <xqreserved>in</xqreserved> <xqvariable>$pc</xqvariable>/people/person,<br>' +
  '<tab><tab><xqvariable>$c</xqvariable> <xqreserved>in</xqreserved> <xqvariable>$t</xqvariable>/profile/interest/@category,<br>' +
  '<tab><tab><xqvariable>$n</xqvariable> <xqreserved>in</xqreserved> <xqvariable>$t</xqvariable>/name/text()<br>' +
  '<tab><xqreserved>where</xqreserved> <xqvariable>$c</xqvariable> <xqreserved>=</xqreserved> <xqvariable>$i</xqvariable><br>' +
  '<tab><xqreserved>return</xqreserved> <xqxml><name></xqxml>{<xqvariable>$n</xqvariable>}<xqxml></name></xqxml><br>' +
  '<xqreserved>return</xqreserved> <xqxml>&lt;category&gt;&lt;id&gt;{</xqxml><xqvariable>$i</xqvariable><xqxml>}&lt;/id&gt;{</xqxml><xqvariable>$p</xqvariable><xqxml>}&lt;/category&gt;</xqxml><br>',
              "Q7": '<xqreserved>let</xqreserved> <xqvariable>$pc</xqvariable> <xqreserved>:= collection</xqreserved>(<xqliteral>"XMarkPeople"</xqliteral>)/site,<br>' +
  '<tab><xqvariable>$cc</xqvariable> <xqreserved>:= collection</xqreserved>(<xqliteral>"XMarkClosedAuctions"</xqliteral>)/site<br>' +
  '<xqreserved>for</xqreserved> <xqvariable>$p</xqvariable> <xqreserved>in</xqreserved> <xqvariable>$pc</xqvariable>/people/person, <xqvariable>$n</xqvariable> <xqreserved>in</xqreserved> <xqvariable>$p</xqvariable>/name/text(), <xqvariable>$i</xqvariable> <xqreserved>in</xqreserved> <xqvariable>$p</xqvariable>/@id<br>' +
  '<xqreserved>let</xqreserved> <xqvariable>$a</xqvariable> <xqreserved>:=</xqreserved><br>' +
  '<tab><xqreserved>for</xqreserved> <xqvariable>$t</xqvariable> <xqreserved>in</xqreserved> <xqvariable>$cc</xqvariable>/closed_auctions/closed_auction, <xqvariable>$b</xqvariable> <xqreserved>in</xqreserved> <xqvariable>$t</xqvariable>/buyer/@person<br>' +
  '<tab><xqreserved>where</xqreserved> <xqvariable>$b</xqvariable> <xqreserved>=</xqreserved> <xqvariable>$i</xqvariable><br>' +
  '<tab><xqreserved>return</xqreserved> <xqvariable>$t</xqvariable><br>' +
  '<xqreserved>let</xqreserved> <xqvariable>$c</xqvariable> <xqreserved>:= count</xqreserved>(<xqvariable>$a</xqvariable>)<br>' +
  '<xqreserved>return</xqreserved> <xqxml>&lt;item person="{</xqxml><xqvariable>$n</xqvariable><xqxml>}">{</xqxml><xqvariable>$c</xqvariable><xqxml>}&lt;/item&gt;</xqxml>',
              "Q8": '<xqreserved>let</xqreserved> <xqvariable>$pc</xqvariable> <xqreserved>:= collection</xqreserved>(<xqliteral>"XMarkPeople"</xqliteral>)/site, <xqvariable>$cc</xqvariable> <xqreserved>:= collection</xqreserved>(<xqliteral>"XMarkClosedAuctions"</xqliteral>)/site<br>' +
'<xqreserved>for</xqreserved> <xqvariable>$p</xqvariable> <xqreserved>in</xqreserved> <xqvariable>$pc</xqvariable>/people/person, <xqvariable>$i</xqvariable> <xqreserved>in</xqreserved> <xqvariable>$p</xqvariable>/@id, <xqvariable>$ad</xqvariable> <xqreserved>in</xqreserved> <xqvariable>$p</xqvariable>/address/country/text()<br>' +
'<xqreserved>let</xqreserved> <xqvariable>$a</xqvariable> <xqreserved>:=</xqreserved><br>' +
'<tab><xqreserved>for</xqreserved> <xqvariable>$c</xqvariable> <xqreserved>in</xqreserved> <xqvariable>$cc</xqvariable>//closed_auction, <xqvariable>$b</xqvariable> <xqreserved>in</xqreserved> <xqvariable>$c</xqvariable>/buyer/@person, <xqvariable>$s</xqvariable> <xqreserved>in</xqreserved> <xqvariable>$c</xqvariable>/seller/@person<br>' +
'<tab><xqreserved>let</xqreserved> <xqvariable>$ir</xqvariable> <xqreserved>:=</xqreserved> <xqvariable>$c</xqvariable>/itemref <br>' +
'<tab><xqreserved>where</xqreserved> <xqvariable>$i</xqvariable> <xqreserved>=</xqreserved> <xqvariable>$b</xqvariable> <xqreserved>or</xqreserved> <xqvariable>$i</xqvariable> <xqreserved>=</xqreserved> <xqvariable>$s</xqvariable><br>' +
'<tab><xqxml>return</xqxml> <xqvariable>$ir</xqvariable><br>' +
'<xqreserved>let</xqreserved> <xqvariable>$n</xqvariable> <xqreserved>:=</xqreserved> <xqvariable>$p</xqvariable>/name<br>' +
'<xqreserved>where</xqreserved> <xqvariable>$ad</xqvariable> <xqreserved>=</xqreserved> <xqliteral>"United States"</xqliteral><br>' +
'<xqreserved>return</xqreserved> <xqxml>&lt;res&gt;{</xqxml>$n<xqxml>,</xqxml>$a<xqxml>}&lt;/res&gt;</xqxml>'
       };

