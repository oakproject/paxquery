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
'<xqreserved>return</xqreserved> <xqxml>&lt;res personid="{</xqxml><xqvariable>$personid</xqvariable><xqxml>}" name="{</xqxml><xqvariable>$personname</xqvariable><xqxml>}" numitems="{</xqxml><xqvariable>$numitems</xqvariable><xqxml>}"&gt;{</xqxml><xqvariable>$cap</xqvariable><xqxml>}&lt;/res&gt;</xqxml>'
       };

