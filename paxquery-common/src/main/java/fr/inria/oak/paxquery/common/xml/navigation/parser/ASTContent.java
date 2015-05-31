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
/* Generated By:JJTree: Do not edit this line. ASTContent.java */

package fr.inria.oak.paxquery.common.xml.navigation.parser;

public class ASTContent extends SimpleNode {
  private String name;

  public ASTContent(int id) {
    super(id);
  }

  public ASTContent(TreePatternParser p, int id) {
    super(p, id);
  }

  public void setName(String n) {
      name = n;
  }

  public String toString() {
      return "Content: " + name;
  }

  public String getInfo() {
      return name;
  }

  /** Accept the visitor. **/
  public Object jjtAccept(TreePatternParserVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }
}
