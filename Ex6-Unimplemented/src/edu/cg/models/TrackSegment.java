package edu.cg.models;

import java.io.File;
import java.util.LinkedList;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.glu.GLUquadric;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;

import edu.cg.algebra.Point;
import edu.cg.models.Car.Materials;

public class TrackSegment implements IRenderable {
	// TODO: Some constants you can use
	public final static double ASPHALT_TEXTURE_WIDTH = 20.0;
	public final static double ASPHALT_TEXTURE_DEPTH = 10.0;
	public final static double GRASS_TEXTURE_WIDTH = 10.0;
	public final static double GRASS_TEXTURE_DEPTH = 10.0;
	public final static double TRACK_LENGTH = 500.0;
	public final static double BOX_LENGTH = 1.5;
	private LinkedList<Point> boxesLocations; // Store the boxes centroids (center points) here.
	// TODO Add additional fields, for example:
	// - Add wooden box model (you will only need one object which can be rendered
	// many times)
	// - Add grass and asphalt textures.
	private SkewedBox box = null;
	private Texture grassTexture;
	private Texture roadTexture;

	public void setDifficulty(double difficulty) {
		// TODO: Set the difficulty of the track segment. Here you decide what are the
		// boxes locations.
		// We provide a simple implementation. You can change it if you want. But if you
		// do decide to use it, then it is your responsibility to understand the logic
		// behind it.
		// Note: In our implementation, the difficulty is the probability of a box to
		// appear in the scene.
		// We divide the scene into rows of boxes and we sample boxes according the
		// difficulty probability.
		difficulty = Math.min(difficulty, 0.95);
		difficulty = Math.max(difficulty, 0.05);
		double numberOfLanes = 4.0;
		double deltaZ = 0.0;
		if (difficulty < 0.25) {
			deltaZ = 100.0;
		} else if (difficulty < 0.5) {
			deltaZ = 75.0;
		} else {
			deltaZ = 50.0;
		}
		boxesLocations = new LinkedList<Point>();
		for (double dz = deltaZ; dz < TRACK_LENGTH - BOX_LENGTH / 2.0; dz += deltaZ) {
			int cnt = 0; // Number of boxes sampled at each row.
			boolean flag = false;
			for (int i = 0; i < 12; i++) {
				double dx = -((double) numberOfLanes / 2.0) * ((ASPHALT_TEXTURE_WIDTH - 2.0) / numberOfLanes)
						+ BOX_LENGTH / 2.0 + i * BOX_LENGTH;
				if (Math.random() < difficulty) {
					boxesLocations.add(new Point(dx, BOX_LENGTH / 2.0, -dz));
					cnt += 1;
				} else if (!flag) {// The first time we don't sample a box then we also don't sample the box next
									// to. We want enough space for the car to pass through.
					i += 1;
					flag = true;
				}
				if (cnt > difficulty * 10) {
					break;
				}
			}
		}
	}

	public TrackSegment(double difficulty) {
		// TODO initialize your fields here.
		// Here by setting up the difficulty, we decide on the boxes locations.
		setDifficulty(difficulty);
		box = new SkewedBox(1.5, /* usingTexture* = */ true);
	}

	@Override
	public void render(GL2 gl) {
		// TODO: Render the track segment
		renderBoxes(gl);
		renderAsphalt(gl);
		// renderGrass(gl);
	}

	@Override
	public void init(GL2 gl) {
		// TODO: Initialize textures.
	}

	public void destroy(GL2 gl) {
		roadTexture.destroy(gl);
		grassTexture.destroy(gl);
		box.destroy(gl);
	}

	// start needs work

	private void renderBoxes(GL2 gl) {
		Materials.setWoodenBoxMaterial(gl);
		for (Point p : boxesLocations) {
			gl.glPushMatrix();
			gl.glTranslated(p.x, p.y, p.z);
			box.render(gl);
			gl.glPopMatrix();
		}
	}

	private void renderAsphalt(GL2 gl) {
		Materials.setAsphaltMaterial(gl);
		gl.glPushMatrix();
		renderQuadraticTexture(gl, roadTexture, 20.0D, 10.0D, 6, 500.0D);
		gl.glPopMatrix();
	}

	private void renderQuadraticTexture(GL2 gl, Texture tex, double quadWidth, double quadDepth, int split,
			double totalDepth) {
		gl.glEnable(3553);
		tex.bind(gl);

		gl.glTexEnvi(8960, 8704, 8448);
		gl.glTexParameteri(3553, 10241, 9987);
		gl.glTexParameteri(3553, 10240, 9729);
		gl.glTexParameteri(3553, 33083, 1);

		gl.glColor3d(1.0D, 0.0D, 0.0D);
		GLU glu = new GLU();
		GLUquadric quad = glu.gluNewQuadric();
		gl.glColor3d(1.0D, 0.0D, 0.0D);
		gl.glNormal3d(0.0D, 1.0D, 0.0D);
		double d = 1.0D / split;
		double dz = quadDepth / split;
		double dx = quadWidth / split;
		for (double tz = 0.0D; tz < totalDepth; tz += quadDepth) {
			for (double i = 0.0D; i < split; i += 1.0D) {
				gl.glBegin(5);
				for (double j = 0.0D; j <= split; j += 1.0D) {
					gl.glTexCoord2d(j * d, (i + 1.0D) * d);
					gl.glVertex3d(-quadWidth / 2.0D + j * dx, 0.0D, -tz - (i + 1.0D) * dz);

					gl.glTexCoord2d(j * d, i * d);
					gl.glVertex3d(-quadWidth / 2.0D + j * dx, 0.0D, -tz - i * dz);
				}
				gl.glEnd();
			}
		}
		glu.gluDeleteQuadric(quad);
		gl.glDisable(3553);
	}

	// end needs work
}
