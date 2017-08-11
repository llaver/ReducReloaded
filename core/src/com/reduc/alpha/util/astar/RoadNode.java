/*    
    Copyright (C) 2012 http://software-talk.org/ (developer@software-talk.org)

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

package com.reduc.alpha.util.astar;

import com.reduc.alpha.util.Pathing;

/**
 * A simple Example implementation of a Node only overriding the sethCosts
 * method; uses manhatten method.
 */
public class RoadNode extends AbstractNode {

        public RoadNode(int xPosition, int yPosition) {
            super(xPosition, yPosition);
            // do other init stuff
        }

        public void sethCosts(AbstractNode endNode) {
            int xPos = this.getxPosition();
            int yPos = this.getyPosition();
            int hCosts = BASICMOVEMENTCOST + (int) Math.abs((Pathing.map[xPos][yPos] * 30)) + (int) (Math.random() * 4);
            this.sethCosts(hCosts);
        }
    
//    public void sethCosts(AbstractNode endNode) {
//        int xPos = this.getxPosition();
//        int yPos = this.getyPosition();
//        int hCosts = (absolute(xPos - endNode.getxPosition())
//            + absolute(yPos - endNode.getyPosition()))
//            + BASICMOVEMENTCOST + (int) Math.abs((TestState.map[xPos][yPos] * 10));
//        this.sethCosts(hCosts);
//    }

        private int absolute(int a) {
            return a > 0 ? a : -a;
        }

}
