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
package fr.inria.oak.paxquery.algebra.operators.binary;

import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import fr.inria.oak.paxquery.algebra.operators.BaseLogicalOperator;
import fr.inria.oak.paxquery.common.xml.navigation.NavigationTreePattern;


/**
 * Generic class that a binary logical operator extends.
 *
 */
public abstract class BaseBinaryOperator extends BaseLogicalOperator{
	
	private static final Log logger = LogFactory.getLog(BaseBinaryOperator.class);
	
	ArrayList<NavigationTreePattern> navigationTreePatternsLeft;
	ArrayList<NavigationTreePattern> navigationTreePatternsRight;
	
	/**
	 * @param left the left child
	 * @param right the right child
	 */
	public BaseBinaryOperator(BaseLogicalOperator left, BaseLogicalOperator right) {
		this.children = new ArrayList<BaseLogicalOperator>();
		this.children.add(left);
		this.children.add(right);
	}
	
	/**
	 * @return the left child
	 */
	public BaseLogicalOperator getLeft() {
		return this.children.get(0);
	}

	/**
	 * 
	 * @param op the logical operator to replace the left child with
	 */
	public void setLeft(BaseLogicalOperator op) {
		this.children.set(0, op);
	}

	
	/**
	 * @return the right child
	 */
	public BaseLogicalOperator getRight() {
		return this.children.get(1);
	}
	
	/**
	 * 
	 * @param op the logical operator to replace the right child with
	 */
	public void setRight(BaseLogicalOperator op) {
		this.children.set(1, op);
	}
	
	/**
	 * Checks whether oldChild is either the left or the right descendant of this operator.
	 * In that case then replaces oldChild for newChild. Otherwise nothing is done.
	 * @param oldChild child to be replaced
	 * @param newChild new child to replace oldchild
	 */
	public void replaceChild(BaseLogicalOperator oldChild, BaseLogicalOperator newChild) {
		if(getLeft() == oldChild)
			setLeft(newChild);
		else if(getRight() == oldChild)
			setRight(newChild);
	}
	
	@Override
	public String getName(){
		StringBuffer sb = new StringBuffer();
		sb.append(this.ownName);
		sb.append("(" + this.getLeft().getName() + "," + this.getRight().getName());
		sb.append(")");
		return new String(sb);
	}
	
	@Override
	public int recursiveDotString(StringBuffer sb, int parentNo, int firstAvailableNo){
			int selfNumber = -1;

			if (this.visible) {
				selfNumber = firstAvailableNo;

				sb.append(
					selfNumber
						+ " [label=\""
						+ this.ownName);
				sb.append("\"] ; \n");

				if (parentNo >= 100) {
					sb.append(parentNo + " -> " + selfNumber + " ; \n");
				}
			}
			int childNumber1 = -1;
			if (this.visible) {
				childNumber1 =
					this.getLeft().recursiveDotString(sb, selfNumber, (firstAvailableNo + 1));
			} else {
				childNumber1 =
					this.getLeft().recursiveDotString(sb, parentNo, firstAvailableNo);
			}
			int childNumber2 = -1;
			if (this.visible) {
				childNumber2 =
					this.getRight().recursiveDotString(sb, selfNumber, childNumber1);
			} else {
				childNumber2 = this.getRight().recursiveDotString(sb, parentNo, childNumber1);
			}
			return childNumber2;
		}

	/* (non-Javadoc)
	 * @see fr.inria.oak.stratosphere.xml.logical.LogicalOperator#recDisplayNRSMD()
	 */
	@Override
	public void recDisplayNRSMD() {
		logger.info("\n" + this.ownName + "of: ");
		this.getLeft().recDisplayNRSMD();
		logger.info("and");
		this.getRight().recDisplayNRSMD();
		logger.info("is ");
		this.nestedMetadata.display();
	}
	
	@Override
	public int getJoinDepth() {
		return 1+Math.max(getLeft().getJoinDepth(), getRight().getJoinDepth());
	}
	
	public ArrayList<NavigationTreePattern> getNavigationTreePatternsLeft() {
		if(this.navigationTreePatternsLeft == null)
			this.navigationTreePatternsLeft = new ArrayList<NavigationTreePattern>();
		return this.navigationTreePatternsLeft;
	}
	
	public void setNavigationTreePatternsLeft(ArrayList<NavigationTreePattern> navigationTreePatternsLeft) {
		this.navigationTreePatternsLeft = navigationTreePatternsLeft;
	}
	
	public void addNavigationTreePatternsLeft(NavigationTreePattern... navigationTreePatternsLeft) {
		if(this.navigationTreePatternsLeft == null)
			this.navigationTreePatternsLeft = new ArrayList<NavigationTreePattern>();
		for(NavigationTreePattern ntp : navigationTreePatternsLeft)
			this.navigationTreePatternsLeft.add(ntp);
	}
	
	public ArrayList<NavigationTreePattern> getNavigationTreePatternsRight() {
		if(this.navigationTreePatternsRight == null)
			this.navigationTreePatternsRight = new ArrayList<NavigationTreePattern>();
		return this.navigationTreePatternsRight;
	}
	
	public void setNavigationTreePatternsRight(ArrayList<NavigationTreePattern> navigationTreePatternsRight) {
		this.navigationTreePatternsRight = navigationTreePatternsRight;
	}
	
	public void addNavigationTreePatternsRight(NavigationTreePattern... navigationTreePatternsRight) {
		if(this.navigationTreePatternsRight == null)
			this.navigationTreePatternsRight = new ArrayList<NavigationTreePattern>();
		for(NavigationTreePattern ntp : navigationTreePatternsRight)
			this.navigationTreePatternsRight.add(ntp);
	}
	
	public ArrayList<NavigationTreePattern> getNavigationTreePatterns() {
		ArrayList<NavigationTreePattern> allNavigationTreePatterns = new ArrayList<NavigationTreePattern>(this.navigationTreePatternsLeft);
		allNavigationTreePatterns.addAll(this.navigationTreePatternsRight);
		return allNavigationTreePatterns;
	}
	
	public boolean isInNavigationTreePatternsLeft(NavigationTreePattern navigationTreePattern) {
		if(this.navigationTreePatternsLeft == null)
			return false;
		for(NavigationTreePattern ntp : this.navigationTreePatternsLeft) {
			if(ntp == navigationTreePattern)
				return true;
		}			
		return false;
	}
	
	public boolean isInNavigationTreePatternsRight(NavigationTreePattern navigationTreePattern) {
		if(this.navigationTreePatternsRight == null)
			return false;
		for(NavigationTreePattern ntp : this.navigationTreePatternsRight) {
			if(ntp == navigationTreePattern)
				return true;
		}
		return false;
	}
}
