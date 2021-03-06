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
package fr.inria.oak.paxquery.common.xml.navigation.parser;

public interface TreePatternParserVisitor
{
  public Object visit(SimpleNode node, Object data);
  public Object visit(ASTStart node, Object data);
  public Object visit(ASTTreePatternSpec node, Object data);
  public Object visit(ASTROOT node, Object data);
  public Object visit(ASTTreePatternOrdered node, Object data);
  public Object visit(ASTDefaultNamespace node, Object data);
  public Object visit(ASTNSPEC node, Object data);
  public Object visit(ASTNE node, Object data);
  public Object visit(ASTNA node, Object data);
  public Object visit(ASTStructural node, Object data);
  public Object visit(ASTOrdered node, Object data);
  public Object visit(ASTInteger node, Object data);
  public Object visit(ASTNavigating node, Object data);
  public Object visit(ASTUpdating node, Object data);
  public Object visit(ASTRequired node, Object data);
  public Object visit(ASTIDSpec node, Object data);
  public Object visit(ASTTagRestriction node, Object data);
  public Object visit(ASTTagFull node, Object data);
  public Object visit(ASTTag node, Object data);
  public Object visit(ASTValRestriction node, Object data);
  public Object visit(ASTValFull node, Object data);
  public Object visit(ASTVal node, Object data);
  public Object visit(ASTCSpec node, Object data);
  public Object visit(ASTPredCode node, Object data);
  public Object visit(ASTContent node, Object data);
  public Object visit(ASTMyID node, Object data);
  public Object visit(ASTDescendant node, Object data);
  public Object visit(ASTChild node, Object data);
  public Object visit(ASTNested node, Object data);
  public Object visit(ASTOuter node, Object data);
  public Object visit(ASTJoin node, Object data);
  public Object visit(ASTSemi node, Object data);
  public Object visit(ASTEdgeSpec node, Object data);
}
/* JavaCC - OriginalChecksum=0be176fbdc05f53aef044ba390f2f786 (do not edit this line) */
