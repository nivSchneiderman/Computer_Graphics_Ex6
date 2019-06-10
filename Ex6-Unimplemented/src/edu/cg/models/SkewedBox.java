package edu.cg.models;

import java.io.File;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GL2ES3;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;

import edu.cg.algebra.Vec;

public class SkewedBox implements IRenderable {

	private double length, height1, height2, depth1, depth2;
	private Texture textureBox = null;
	private boolean usingTexture = false;

	public SkewedBox() { // Default constructor
		length = 0.1;
		height1 = 0.2;
		height2 = 0.1;
		depth1 = 0.2;
		depth2 = 0.1;
	};

	public SkewedBox(double length, double h1, double h2, double d1, double d2) {
		this.length = length;
		this.height1 = h1;
		this.height2 = h2;
		this.depth1 = d1;
		this.depth2 = d2;
	}
	
	public SkewedBox(double length, double h1, double h2, double d1, double d2, boolean usingTexture) {
		this.length = length;
		this.height1 = h1;
		this.height2 = h2;
		this.depth1 = d1;
		this.depth2 = d2;
		this.usingTexture = usingTexture;
	}

	public SkewedBox(double length, boolean usingTexture) {
		this.length = length;
		depth1 = length;
		depth2 = length;
		height1 = length;
		height2 = length;
		this.usingTexture = usingTexture;
	}
	
	public void render(GL2 gl) {
		
		if (usingTexture) {
			assert ((textureBox != null) && (gl != null));
			initTextureParams(gl);
		}

		//front sqaure 
		gl.glNormal3d(1, 0, 0);
		gl.glBegin(gl.GL_QUADS);
		gl.glTexCoord2d(0,0);
		gl.glVertex3d(length / 2, 0,  depth2 / 2);
		gl.glTexCoord2d(0,1);
		gl.glVertex3d(length / 2, 0, -depth2 / 2);
		gl.glTexCoord2d(1,1);
		gl.glVertex3d(length / 2, height2, -depth2 / 2);
		gl.glTexCoord2d(1,0);
		gl.glVertex3d(length / 2, height2,  depth2 / 2);
		gl.glEnd();
		
		 //back box
		gl.glNormal3d(-1, 0, 0);
        gl.glBegin(gl.GL_QUADS);
        gl.glTexCoord2d(0,0);
        gl.glVertex3d(-length / 2,    0   , -depth1/2);
        gl.glTexCoord2d(0,1);
        gl.glVertex3d(-length / 2,    0   ,  depth1/2);
        gl.glTexCoord2d(1,1);
        gl.glVertex3d(-length / 2, height1,  depth1/2);
        gl.glTexCoord2d(1,0);
        gl.glVertex3d(-length / 2, height1, -depth1/2);
        gl.glEnd();
		
        Vec normalToSurface = new Vec(height1 - height2, 1, 0).normalize();

	    //upper box
	    gl.glNormal3d(normalToSurface.x, normalToSurface.y, normalToSurface.z);
	    gl.glBegin(gl.GL_QUADS);
	    gl.glTexCoord2d(0,0);
	    gl.glVertex3d(-length / 2, height1, depth1 / 2);
	    gl.glTexCoord2d(0, 1);
	    gl.glVertex3d( length / 2, height2, depth2 / 2);
	    gl.glTexCoord2d(1, 1);
	    gl.glVertex3d( length / 2, height2, -depth2 / 2);
	    gl.glTexCoord2d(1, 0);
	    gl.glVertex3d(-length / 2, height1, -depth1 / 2);
	    gl.glEnd();
	    
	    //lower box
	    gl.glBegin(gl.GL_QUADS);
	    gl.glNormal3d(0,-1, 0);
	    gl.glTexCoord2d(0,0);
	    gl.glVertex3d(-length / 2, 0,  depth1 / 2);
	    gl.glTexCoord2d(0,1);
	    gl.glVertex3d(-length / 2, 0, -depth1 / 2);
	    gl.glTexCoord2d(1,1);
	    gl.glVertex3d(length /  2, 0, -depth2 / 2);
	    gl.glTexCoord2d(1,0);
	    gl.glVertex3d(length / 2,  0,  depth2 / 2);
	    gl.glEnd();
	    
	    normalToSurface = new Vec(depth1 - depth2, 0, 1).normalize();
	    
	    //right box 
	    gl.glBegin(gl.GL_QUADS);
	    gl.glNormal3d(normalToSurface.x, normalToSurface.y, normalToSurface.z);
	    gl.glTexCoord2d(0, 0);
	    gl.glVertex3d(-length / 2, height1, depth1 / 2);
	    gl.glTexCoord2d(0,1);
	    gl.glVertex3d(-length / 2, 0, depth1 / 2);
	    gl.glTexCoord2d(1,1);
	    gl.glVertex3d( length / 2, 0, depth2 / 2);
	    gl.glTexCoord2d(1,0);
	    gl.glVertex3d( length / 2, height2, depth2 / 2);
	    gl.glEnd();
	    
	    
	    normalToSurface.z = -1;
	    gl.glBegin(gl.GL_QUADS);
	    gl.glNormal3d(normalToSurface.x, normalToSurface.y, normalToSurface.z);
	    gl.glTexCoord2d(0,0);
	    gl.glVertex3d(-length / 2, 0, -depth1 / 2);
	    gl.glTexCoord2d(0,1);
	    gl.glVertex3d(-length / 2, height1, -depth1 / 2);
	    gl.glTexCoord2d(1,1);
	    gl.glVertex3d(length / 2, height2, -depth2 / 2);
	    gl.glTexCoord2d(1,0);
	    gl.glVertex3d(length / 2, 0, -depth2 / 2);
	    gl.glEnd();	    
	  
	}

//==================== enum ==========================

	private void initTextureParams(GL2 gl) {
		
		gl.glEnable(3553);
		textureBox.bind(gl);
		gl.glTexEnvi(8960, 8704, 8448);
		gl.glTexParameteri(3553, 10241, 9987);
		gl.glTexParameteri(3553, 10240, 9729);
		gl.glTexParameteri(3553, 33083, 1);
		
//=====================    end ========================= 		
	}

	@Override
	public void init(GL2 gl) {
		if (usingTexture) {
			try {
				textureBox = TextureIO.newTexture(new File("Textures/WoodBoxTexture.jpg"), true);
			} catch (Exception e) {
				System.err.print("Unable to read WoodBoxTexture.jpg texture : " + e.getMessage());
			}
		}
	}
	
		
	@Override
	public String toString() {
		return "SkewedBox";
	}

	@Override
	public void destroy(GL2 gl) {
		if (usingTexture) {
			textureBox.destroy(gl);
			textureBox = null;
		}
	}
}
