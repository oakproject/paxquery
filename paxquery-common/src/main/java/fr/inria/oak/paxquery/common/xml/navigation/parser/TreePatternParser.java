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

import java.io.*;
import fr.inria.oak.paxquery.common.xml.navigation.NavigationTreePattern;

public class TreePatternParser/*@bgen(jjtree)*/implements TreePatternParserTreeConstants, TreePatternParserConstants {/*@bgen(jjtree)*/
  protected JJTTreePatternParserState jjtree = new JJTTreePatternParserState();

  final public ASTStart Start(String TreePatternName) throws ParseException {
                                                 /*@bgen(jjtree) Start */
  ASTStart jjtn000 = new ASTStart(JJTSTART);
  boolean jjtc000 = true;
  jjtree.openNodeScope(jjtn000);
    try {
      TreePatternSpec();
      jj_consume_token(0);
    jjtree.closeNodeScope(jjtn000, true);
    jjtc000 = false;
    {if (true) return jjtn000;}
    } catch (Throwable jjte000) {
    if (jjtc000) {
      jjtree.clearNodeScope(jjtn000);
      jjtc000 = false;
    } else {
      jjtree.popNode();
    }
    if (jjte000 instanceof RuntimeException) {
      {if (true) throw (RuntimeException)jjte000;}
    }
    if (jjte000 instanceof ParseException) {
      {if (true) throw (ParseException)jjte000;}
    }
    {if (true) throw (Error)jjte000;}
    } finally {
    if (jjtc000) {
      jjtree.closeNodeScope(jjtn000, true);
    }
    }
    throw new Error("Missing return statement in function");
  }

  final public void TreePatternSpec() throws ParseException {
            ASTTreePatternSpec jjtn001 = new ASTTreePatternSpec(JJTTREEPATTERNSPEC);
            boolean jjtc001 = true;
            jjtree.openNodeScope(jjtn001);
    try {
      IsOrdered();
      IsRoot();
      DefaultNamespace();
      NSpec();
      jj_consume_token(13);
      ESpec();
    } catch (Throwable jjte001) {
            if (jjtc001) {
              jjtree.clearNodeScope(jjtn001);
              jjtc001 = false;
            } else {
              jjtree.popNode();
            }
            if (jjte001 instanceof RuntimeException) {
              {if (true) throw (RuntimeException)jjte001;}
            }
            if (jjte001 instanceof ParseException) {
              {if (true) throw (ParseException)jjte001;}
            }
            {if (true) throw (Error)jjte001;}
    } finally {
            if (jjtc001) {
              jjtree.closeNodeScope(jjtn001, true);
            }
    }
  }

  final public void IsRoot() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 14:
           ASTROOT jjtn001 = new ASTROOT(JJTROOT);
           boolean jjtc001 = true;
           jjtree.openNodeScope(jjtn001);
      try {
        jj_consume_token(14);
      } finally {
           if (jjtc001) {
             jjtree.closeNodeScope(jjtn001, true);
           }
      }
      break;
    default:
      jj_la1[0] = jj_gen;
      ;
    }
  }

  final public void IsOrdered() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 15:
    case 16:
           ASTTreePatternOrdered jjtn001 = new ASTTreePatternOrdered(JJTTREEPATTERNORDERED);
           boolean jjtc001 = true;
           jjtree.openNodeScope(jjtn001);
      try {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case 15:
          jj_consume_token(15);
          break;
        case 16:
          jj_consume_token(16);
          break;
        default:
          jj_la1[1] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
      } finally {
           if (jjtc001) {
             jjtree.closeNodeScope(jjtn001, true);
           }
      }
      break;
    default:
      jj_la1[2] = jj_gen;
      ;
    }
  }

  final public void DefaultNamespace() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 17:
           ASTDefaultNamespace jjtn001 = new ASTDefaultNamespace(JJTDEFAULTNAMESPACE);
           boolean jjtc001 = true;
           jjtree.openNodeScope(jjtn001);
      try {
        jj_consume_token(17);
        Content();
        jj_consume_token(18);
      } catch (Throwable jjte001) {
           if (jjtc001) {
             jjtree.clearNodeScope(jjtn001);
             jjtc001 = false;
           } else {
             jjtree.popNode();
           }
           if (jjte001 instanceof RuntimeException) {
             {if (true) throw (RuntimeException)jjte001;}
           }
           if (jjte001 instanceof ParseException) {
             {if (true) throw (ParseException)jjte001;}
           }
           {if (true) throw (Error)jjte001;}
      } finally {
           if (jjtc001) {
             jjtree.closeNodeScope(jjtn001, true);
           }
      }
      break;
    default:
      jj_la1[3] = jj_gen;
      ;
    }
  }

  final public void NSpec() throws ParseException {
          ASTNSPEC jjtn001 = new ASTNSPEC(JJTNSPEC);
          boolean jjtc001 = true;
          jjtree.openNodeScope(jjtn001);
    try {
      label_1:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case 19:
        case 20:
          ;
          break;
        default:
          jj_la1[4] = jj_gen;
          break label_1;
        }
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case 19:
          NE();
          break;
        case 20:
          NA();
          break;
        default:
          jj_la1[5] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
      }
    } catch (Throwable jjte001) {
          if (jjtc001) {
            jjtree.clearNodeScope(jjtn001);
            jjtc001 = false;
          } else {
            jjtree.popNode();
          }
          if (jjte001 instanceof RuntimeException) {
            {if (true) throw (RuntimeException)jjte001;}
          }
          if (jjte001 instanceof ParseException) {
            {if (true) throw (ParseException)jjte001;}
          }
          {if (true) throw (Error)jjte001;}
    } finally {
          if (jjtc001) {
            jjtree.closeNodeScope(jjtn001, true);
          }
    }
  }

  final public void NE() throws ParseException {
          ASTNE jjtn001 = new ASTNE(JJTNE);
          boolean jjtc001 = true;
          jjtree.openNodeScope(jjtn001);
    try {
      jj_consume_token(19);
      MyID();
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case ATTRID:
        IDSpec();
        break;
      default:
        jj_la1[6] = jj_gen;
        ;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 26:
      case 27:
        TSpec();
        break;
      default:
        jj_la1[7] = jj_gen;
        ;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case ATTRVAL:
      case 28:
        VSpec();
        break;
      default:
        jj_la1[8] = jj_gen;
        ;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case ATTRCONT:
        CSpec();
        break;
      default:
        jj_la1[9] = jj_gen;
        ;
      }
    } catch (Throwable jjte001) {
          if (jjtc001) {
            jjtree.clearNodeScope(jjtn001);
            jjtc001 = false;
          } else {
            jjtree.popNode();
          }
          if (jjte001 instanceof RuntimeException) {
            {if (true) throw (RuntimeException)jjte001;}
          }
          if (jjte001 instanceof ParseException) {
            {if (true) throw (ParseException)jjte001;}
          }
          {if (true) throw (Error)jjte001;}
    } finally {
          if (jjtc001) {
            jjtree.closeNodeScope(jjtn001, true);
          }
    }
  }

  final public void NA() throws ParseException {
          ASTNA jjtn001 = new ASTNA(JJTNA);
          boolean jjtc001 = true;
          jjtree.openNodeScope(jjtn001);
    try {
      jj_consume_token(20);
      MyID();
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case ATTRID:
        IDSpec();
        break;
      default:
        jj_la1[10] = jj_gen;
        ;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 26:
      case 27:
        TSpec();
        break;
      default:
        jj_la1[11] = jj_gen;
        ;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case ATTRVAL:
      case 28:
        VSpec();
        break;
      default:
        jj_la1[12] = jj_gen;
        ;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case ATTRCONT:
        CSpec();
        break;
      default:
        jj_la1[13] = jj_gen;
        ;
      }
    } catch (Throwable jjte001) {
          if (jjtc001) {
            jjtree.clearNodeScope(jjtn001);
            jjtc001 = false;
          } else {
            jjtree.popNode();
          }
          if (jjte001 instanceof RuntimeException) {
            {if (true) throw (RuntimeException)jjte001;}
          }
          if (jjte001 instanceof ParseException) {
            {if (true) throw (ParseException)jjte001;}
          }
          {if (true) throw (Error)jjte001;}
    } finally {
          if (jjtc001) {
            jjtree.closeNodeScope(jjtn001, true);
          }
    }
  }

  final public void IDSpec() throws ParseException {
          ASTIDSpec jjtn007 = new ASTIDSpec(JJTIDSPEC);
          boolean jjtc007 = true;
          jjtree.openNodeScope(jjtn007);
    try {
      jj_consume_token(ATTRID);
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 21:
                          ASTStructural jjtn001 = new ASTStructural(JJTSTRUCTURAL);
                          boolean jjtc001 = true;
                          jjtree.openNodeScope(jjtn001);
        try {
          jj_consume_token(21);
        } finally {
                          if (jjtc001) {
                            jjtree.closeNodeScope(jjtn001, true);
                          }
        }
        break;
      case 15:
                          ASTOrdered jjtn002 = new ASTOrdered(JJTORDERED);
                          boolean jjtc002 = true;
                          jjtree.openNodeScope(jjtn002);
        try {
          jj_consume_token(15);
        } finally {
                          if (jjtc002) {
                            jjtree.closeNodeScope(jjtn002, true);
                          }
        }
        break;
      case 22:
                          ASTInteger jjtn003 = new ASTInteger(JJTINTEGER);
                          boolean jjtc003 = true;
                          jjtree.openNodeScope(jjtn003);
        try {
          jj_consume_token(22);
        } finally {
                          if (jjtc003) {
                            jjtree.closeNodeScope(jjtn003, true);
                          }
        }
        break;
      case 23:
                          ASTNavigating jjtn004 = new ASTNavigating(JJTNAVIGATING);
                          boolean jjtc004 = true;
                          jjtree.openNodeScope(jjtn004);
        try {
          jj_consume_token(23);
        } finally {
                          if (jjtc004) {
                            jjtree.closeNodeScope(jjtn004, true);
                          }
        }
        break;
      case 24:
                          ASTUpdating jjtn005 = new ASTUpdating(JJTUPDATING);
                          boolean jjtc005 = true;
                          jjtree.openNodeScope(jjtn005);
        try {
          jj_consume_token(24);
        } finally {
                          if (jjtc005) {
                            jjtree.closeNodeScope(jjtn005, true);
                          }
        }
        break;
      default:
        jj_la1[14] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 25:
              ASTRequired jjtn006 = new ASTRequired(JJTREQUIRED);
              boolean jjtc006 = true;
              jjtree.openNodeScope(jjtn006);
        try {
          jj_consume_token(25);
        } finally {
              if (jjtc006) {
                jjtree.closeNodeScope(jjtn006, true);
              }
        }
        break;
      default:
        jj_la1[15] = jj_gen;
        ;
      }
    } finally {
          if (jjtc007) {
            jjtree.closeNodeScope(jjtn007, true);
          }
    }
  }

  final public void TSpec() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 26:
                    ASTTagRestriction jjtn001 = new ASTTagRestriction(JJTTAGRESTRICTION);
                    boolean jjtc001 = true;
                    jjtree.openNodeScope(jjtn001);
      try {
        jj_consume_token(26);
        Content();
        jj_consume_token(18);
      } catch (Throwable jjte001) {
                    if (jjtc001) {
                      jjtree.clearNodeScope(jjtn001);
                      jjtc001 = false;
                    } else {
                      jjtree.popNode();
                    }
                    if (jjte001 instanceof RuntimeException) {
                      {if (true) throw (RuntimeException)jjte001;}
                    }
                    if (jjte001 instanceof ParseException) {
                      {if (true) throw (ParseException)jjte001;}
                    }
                    {if (true) throw (Error)jjte001;}
      } finally {
                    if (jjtc001) {
                      jjtree.closeNodeScope(jjtn001, true);
                    }
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 27:
                                                             ASTTagFull jjtn003 = new ASTTagFull(JJTTAGFULL);
                                                             boolean jjtc003 = true;
                                                             jjtree.openNodeScope(jjtn003);
        try {
          jj_consume_token(27);
          switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
          case 25:
                                                                     ASTRequired jjtn002 = new ASTRequired(JJTREQUIRED);
                                                                     boolean jjtc002 = true;
                                                                     jjtree.openNodeScope(jjtn002);
            try {
              jj_consume_token(25);
            } finally {
                                                                     if (jjtc002) {
                                                                       jjtree.closeNodeScope(jjtn002, true);
                                                                     }
            }
            break;
          default:
            jj_la1[16] = jj_gen;
            ;
          }
        } finally {
                                                             if (jjtc003) {
                                                               jjtree.closeNodeScope(jjtn003, true);
                                                             }
        }
        break;
      default:
        jj_la1[17] = jj_gen;
        ;
      }
      break;
    case 27:
      jj_consume_token(27);
                                                                                                               ASTTag jjtn005 = new ASTTag(JJTTAG);
                                                                                                               boolean jjtc005 = true;
                                                                                                               jjtree.openNodeScope(jjtn005);
      try {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case 25:
                                                                                                                 ASTRequired jjtn004 = new ASTRequired(JJTREQUIRED);
                                                                                                                 boolean jjtc004 = true;
                                                                                                                 jjtree.openNodeScope(jjtn004);
          try {
            jj_consume_token(25);
          } finally {
                                                                                                                 if (jjtc004) {
                                                                                                                   jjtree.closeNodeScope(jjtn004, true);
                                                                                                                 }
          }
          break;
        default:
          jj_la1[18] = jj_gen;
          ;
        }
      } finally {
                                                                                                               if (jjtc005) {
                                                                                                                 jjtree.closeNodeScope(jjtn005, true);
                                                                                                               }
      }
      break;
    default:
      jj_la1[19] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  final public void VSpec() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 28:
                    ASTValRestriction jjtn001 = new ASTValRestriction(JJTVALRESTRICTION);
                    boolean jjtc001 = true;
                    jjtree.openNodeScope(jjtn001);
      try {
        jj_consume_token(28);
        PredCode();
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case STRING:
          Content();
          break;
        case INTEGER_LITERAL:
          MyID();
          break;
        default:
          jj_la1[20] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
        jj_consume_token(18);
      } catch (Throwable jjte001) {
                    if (jjtc001) {
                      jjtree.clearNodeScope(jjtn001);
                      jjtc001 = false;
                    } else {
                      jjtree.popNode();
                    }
                    if (jjte001 instanceof RuntimeException) {
                      {if (true) throw (RuntimeException)jjte001;}
                    }
                    if (jjte001 instanceof ParseException) {
                      {if (true) throw (ParseException)jjte001;}
                    }
                    {if (true) throw (Error)jjte001;}
      } finally {
                    if (jjtc001) {
                      jjtree.closeNodeScope(jjtn001, true);
                    }
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case ATTRVAL:
                                                                                   ASTValFull jjtn003 = new ASTValFull(JJTVALFULL);
                                                                                   boolean jjtc003 = true;
                                                                                   jjtree.openNodeScope(jjtn003);
        try {
          jj_consume_token(ATTRVAL);
          switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
          case 25:
                                                                                           ASTRequired jjtn002 = new ASTRequired(JJTREQUIRED);
                                                                                           boolean jjtc002 = true;
                                                                                           jjtree.openNodeScope(jjtn002);
            try {
              jj_consume_token(25);
            } finally {
                                                                                           if (jjtc002) {
                                                                                             jjtree.closeNodeScope(jjtn002, true);
                                                                                           }
            }
            break;
          default:
            jj_la1[21] = jj_gen;
            ;
          }
        } finally {
                                                                                   if (jjtc003) {
                                                                                     jjtree.closeNodeScope(jjtn003, true);
                                                                                   }
        }
        break;
      default:
        jj_la1[22] = jj_gen;
        ;
      }
      break;
    case ATTRVAL:
      jj_consume_token(ATTRVAL);
                                                                                                                                     ASTVal jjtn005 = new ASTVal(JJTVAL);
                                                                                                                                     boolean jjtc005 = true;
                                                                                                                                     jjtree.openNodeScope(jjtn005);
      try {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case 25:
                                                                                                                                       ASTRequired jjtn004 = new ASTRequired(JJTREQUIRED);
                                                                                                                                       boolean jjtc004 = true;
                                                                                                                                       jjtree.openNodeScope(jjtn004);
          try {
            jj_consume_token(25);
          } finally {
                                                                                                                                       if (jjtc004) {
                                                                                                                                         jjtree.closeNodeScope(jjtn004, true);
                                                                                                                                       }
          }
          break;
        default:
          jj_la1[23] = jj_gen;
          ;
        }
      } finally {
                                                                                                                                     if (jjtc005) {
                                                                                                                                       jjtree.closeNodeScope(jjtn005, true);
                                                                                                                                     }
      }
      break;
    default:
      jj_la1[24] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  final public void CSpec() throws ParseException {
                       /*@bgen(jjtree) CSpec */
  ASTCSpec jjtn000 = new ASTCSpec(JJTCSPEC);
  boolean jjtc000 = true;
  jjtree.openNodeScope(jjtn000);
    try {
      jj_consume_token(ATTRCONT);
    } finally {
          if (jjtc000) {
            jjtree.closeNodeScope(jjtn000, true);
          }
    }
  }

  final public void PredCode() throws ParseException {
 /*@bgen(jjtree) PredCode */
  ASTPredCode jjtn000 = new ASTPredCode(JJTPREDCODE);
  boolean jjtc000 = true;
  jjtree.openNodeScope(jjtn000);Token t;
    try {
      t = jj_consume_token(PREDCODE);
    jjtree.closeNodeScope(jjtn000, true);
    jjtc000 = false;
    jjtn000.setName(t.image);
    } finally {
    if (jjtc000) {
      jjtree.closeNodeScope(jjtn000, true);
    }
    }
  }

  final public void Content() throws ParseException {
 /*@bgen(jjtree) Content */
  ASTContent jjtn000 = new ASTContent(JJTCONTENT);
  boolean jjtc000 = true;
  jjtree.openNodeScope(jjtn000);Token t;
    try {
      t = jj_consume_token(STRING);
    jjtree.closeNodeScope(jjtn000, true);
    jjtc000 = false;
    jjtn000.setName(t.image);
    } finally {
    if (jjtc000) {
      jjtree.closeNodeScope(jjtn000, true);
    }
    }
  }

  final public void MyID() throws ParseException {
 /*@bgen(jjtree) MyID */
  ASTMyID jjtn000 = new ASTMyID(JJTMYID);
  boolean jjtc000 = true;
  jjtree.openNodeScope(jjtn000);Token t;
    try {
      t = jj_consume_token(INTEGER_LITERAL);
    jjtree.closeNodeScope(jjtn000, true);
    jjtc000 = false;
    jjtn000.setName(t.image);
    } finally {
    if (jjtc000) {
      jjtree.closeNodeScope(jjtn000, true);
    }
    }
  }

  final public void ESpec() throws ParseException {
    label_2:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case INTEGER_LITERAL:
        ;
        break;
      default:
        jj_la1[25] = jj_gen;
        break label_2;
      }
            ASTEdgeSpec jjtn007 = new ASTEdgeSpec(JJTEDGESPEC);
            boolean jjtc007 = true;
            jjtree.openNodeScope(jjtn007);
      try {
        MyID();
        jj_consume_token(29);
        MyID();
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case 30:
                                 ASTDescendant jjtn001 = new ASTDescendant(JJTDESCENDANT);
                                 boolean jjtc001 = true;
                                 jjtree.openNodeScope(jjtn001);
          try {
            jj_consume_token(30);
          } finally {
                                 if (jjtc001) {
                                   jjtree.closeNodeScope(jjtn001, true);
                                 }
          }
          break;
        case 14:
                                                   ASTChild jjtn002 = new ASTChild(JJTCHILD);
                                                   boolean jjtc002 = true;
                                                   jjtree.openNodeScope(jjtn002);
          try {
            jj_consume_token(14);
          } finally {
                                                   if (jjtc002) {
                                                     jjtree.closeNodeScope(jjtn002, true);
                                                   }
          }
          break;
        default:
          jj_la1[26] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case 23:
                                                                 ASTNested jjtn003 = new ASTNested(JJTNESTED);
                                                                 boolean jjtc003 = true;
                                                                 jjtree.openNodeScope(jjtn003);
          try {
            jj_consume_token(23);
          } finally {
                                                                 if (jjtc003) {
                                                                   jjtree.closeNodeScope(jjtn003, true);
                                                                 }
          }
          break;
        default:
          jj_la1[27] = jj_gen;
          ;
        }
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case 15:
                                                                                ASTOuter jjtn004 = new ASTOuter(JJTOUTER);
                                                                                boolean jjtc004 = true;
                                                                                jjtree.openNodeScope(jjtn004);
          try {
            jj_consume_token(15);
          } finally {
                                                                                if (jjtc004) {
                                                                                  jjtree.closeNodeScope(jjtn004, true);
                                                                                }
          }
          break;
        case 31:
                                                                                            ASTJoin jjtn005 = new ASTJoin(JJTJOIN);
                                                                                            boolean jjtc005 = true;
                                                                                            jjtree.openNodeScope(jjtn005);
          try {
            jj_consume_token(31);
          } finally {
                                                                                            if (jjtc005) {
                                                                                              jjtree.closeNodeScope(jjtn005, true);
                                                                                            }
          }
          break;
        case 21:
                                                                                                       ASTSemi jjtn006 = new ASTSemi(JJTSEMI);
                                                                                                       boolean jjtc006 = true;
                                                                                                       jjtree.openNodeScope(jjtn006);
          try {
            jj_consume_token(21);
          } finally {
                                                                                                       if (jjtc006) {
                                                                                                         jjtree.closeNodeScope(jjtn006, true);
                                                                                                       }
          }
          break;
        default:
          jj_la1[28] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
      } catch (Throwable jjte007) {
            if (jjtc007) {
              jjtree.clearNodeScope(jjtn007);
              jjtc007 = false;
            } else {
              jjtree.popNode();
            }
            if (jjte007 instanceof RuntimeException) {
              {if (true) throw (RuntimeException)jjte007;}
            }
            if (jjte007 instanceof ParseException) {
              {if (true) throw (ParseException)jjte007;}
            }
            {if (true) throw (Error)jjte007;}
      } finally {
            if (jjtc007) {
              jjtree.closeNodeScope(jjtn007, true);
            }
      }
    }
  }

  /** Generated Token Manager. */
  public TreePatternParserTokenManager token_source;
  SimpleCharStream jj_input_stream;
  /** Current token. */
  public Token token;
  /** Next token. */
  public Token jj_nt;
  private int jj_ntk;
  private int jj_gen;
  final private int[] jj_la1 = new int[29];
  static private int[] jj_la1_0;
  static {
      jj_la1_init_0();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x4000,0x18000,0x18000,0x20000,0x180000,0x180000,0x200,0xc000000,0x10000400,0x800,0x200,0xc000000,0x10000400,0x800,0x1e08000,0x2000000,0x2000000,0x8000000,0x2000000,0xc000000,0xc0,0x2000000,0x400,0x2000000,0x10000400,0x40,0x40004000,0x800000,0x80208000,};
   }

  /** Constructor with InputStream. */
  public TreePatternParser(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public TreePatternParser(java.io.InputStream stream, String encoding) {
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new TreePatternParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 29; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jjtree.reset();
    jj_gen = 0;
    for (int i = 0; i < 29; i++) jj_la1[i] = -1;
  }

  /** Constructor. */
  public TreePatternParser(java.io.Reader stream) {
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new TreePatternParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 29; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jjtree.reset();
    jj_gen = 0;
    for (int i = 0; i < 29; i++) jj_la1[i] = -1;
  }

  /** Constructor with generated Token Manager. */
  public TreePatternParser(TreePatternParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 29; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(TreePatternParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jjtree.reset();
    jj_gen = 0;
    for (int i = 0; i < 29; i++) jj_la1[i] = -1;
  }

  private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }


/** Get the next Token. */
  final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  private int[] jj_expentry;
  private int jj_kind = -1;

  /** Generate ParseException. */
  public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[32];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 29; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 32; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  /** Enable tracing. */
  final public void enable_tracing() {
  }

  /** Disable tracing. */
  final public void disable_tracing() {
  }

}
