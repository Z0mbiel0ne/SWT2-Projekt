/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fhdo.swt2.udrive.model;

import fhdo.swt2.udrive.view.UpdateCredit;
import javax.swing.JTable;

/**
 *
 * @author Marcel
 */
public class UpdateCreditFactory 
{
  UpdateCredit updateCredit;
  
  public UpdateCreditFactory()
  {
      
  }
  
  public  UpdateCredit newInsatnce(int value, JTable jtable1)
  {
      updateCredit = new UpdateCredit(value, jtable1);
      return updateCredit;
  }
}
