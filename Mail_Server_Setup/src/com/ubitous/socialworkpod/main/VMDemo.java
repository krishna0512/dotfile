package com.ubitous.socialworkpod.main;
import java.io.StringWriter;
import java.io.Writer;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
//import org.apache.velocity.tools.generic.RenderTool;

public class VMDemo {

  public static void main(String[] args) throws Exception {
    Velocity.init();
    Template t = Velocity.getTemplate("./src/com/ubitous/socialworkpod/template/VMDemo.vm");

    VelocityContext ctx = new VelocityContext();

    Writer writer = new StringWriter();
    t.merge(ctx, writer);

    System.out.println(writer);
  }
}