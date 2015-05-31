/*******************************************************************************
 * Copyright (C) 2013, 2014, 2015 by Inria and Paris-Sud University
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
/* Generated By:JJTree: Do not edit this line. ASTPredCode.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package fr.inria.oak.paxquery.common.xml.navigation.parser;

public
class ASTPredCode extends SimpleNode {
	  private String name;

  public ASTPredCode(int id) {
    super(id);
  }

  public ASTPredCode(TreePatternParser p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(TreePatternParserVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }

  public void setName(String n) {
      name = n;
  }
  
  public String getInfo() {
	  return this.name;
  }
}
/* JavaCC - OriginalChecksum=0833501080baf6b1281253080ecdd0bc (do not edit this line) */
