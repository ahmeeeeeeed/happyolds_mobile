/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.GUI;

import com.codename1.components.InfiniteProgress;
import com.codename1.ui.Image;
import com.codename1.ui.util.Resources;

/**
 *
 * @author ahmed
 */
public class Loader {
    
    public Loader(Resources res){
        
        Image mask = res.getImage("loaderr.png");
                 InfiniteProgress ip = new InfiniteProgress();
                 ip.setAnimation(mask);
                 ip.showInfiniteBlocking();
                 
    }
    
}
