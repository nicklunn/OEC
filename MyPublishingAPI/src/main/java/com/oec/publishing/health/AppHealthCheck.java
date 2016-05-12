/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oec.publishing.health;

import com.codahale.metrics.health.HealthCheck;

/**
 *
 * @author lunnn
 */
public class AppHealthCheck extends HealthCheck{
    public AppHealthCheck() {
        //super("publishing");
    }

    @Override
    protected Result check() throws Exception {        
        return Result.healthy();
    }
}
