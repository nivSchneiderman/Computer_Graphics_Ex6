package edu.cg.models.Car;

import com.jogamp.opengl.GL2;

public class Materials {
	// TODO: Use this class to update the color of the vertexes when drawing.
	private static final float DARK_GRAY[] = { 0.2f, 0.2f, 0.2f };
	private static final float BLACK[] = { 0.05f, 0.05f, 0.05f };

	public static void SetBlackMetalMaterial(GL2 gl) {
		float[] mat_ambient = { 0.0f, 0.0f, 0.0f, 1.0f };
		float[] mat_diffuse = { 0.01f, 0.01f, 0.01f, 1.0f };
		float[] mat_specular = { 0.5f, 0.5f, 0.5f, 1.0f };
		float shine = 32.0f;
		gl.glMaterialf(GL2.GL_FRONT, GL2.GL_SHININESS, shine);
		gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_AMBIENT, mat_ambient, 0);
		gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_DIFFUSE, mat_diffuse, 0);
		gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_SPECULAR, mat_specular, 0);
	}

	public static void SetRedMetalMaterial(GL2 gl) {
		float[] mat_ambient = { 0.17f, 0.01f, 0.012f, 1.0f };
		float[] mat_diffuse = { 0.61f, 0.04f, 0.04f, 1.0f };
		float[] mat_specular = { 0.73f, 0.63f, 0.63f, 1.0f };
		float shine = 76.8f;
		gl.glMaterialf(GL2.GL_FRONT, GL2.GL_SHININESS, shine);
		gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_AMBIENT, mat_ambient, 0);
		gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_DIFFUSE, mat_diffuse, 0);
		gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_SPECULAR, mat_specular, 0);
	}

	public static void SetDarkRedMetalMaterial(GL2 gl) {
		float[] mat_ambient = { 0f, 0f, 0f, 1f };
		float[] mat_diffuse = { 0.4f, 0f, 0f, 1f };
		float[] mat_specular = { 0.4f, 0.3f, 0.3f, 1f };
		float shine = 32f;
		gl.glMaterialf(GL2.GL_FRONT, GL2.GL_SHININESS, shine);
		gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_AMBIENT, mat_ambient, 0);
		gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_DIFFUSE, mat_diffuse, 0);
		gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_SPECULAR, mat_specular, 0);
	}

	public static void SetDarkGreyMetalMaterial(GL2 gl) {
		float[] mat_ambient = { 0.25f, 0.25f, 0.25f, 1f };
		float[] mat_diffuse = { 0.4f, 0.4f, 0.4f, 1f };
		float[] mat_specular = { 0.775f, 0.775f, 0.775f, 1f };
		float shine = 76.8f;
		gl.glMaterialf(GL2.GL_FRONT, GL2.GL_SHININESS, shine);
		gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_AMBIENT, mat_ambient, 0);
		gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_DIFFUSE, mat_diffuse, 0);
		gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_SPECULAR, mat_specular, 0);
	}

	public static void setMaterialTire(GL2 gl) {
		float[] col = { 0.05f, 0.05f, 0.05f };
		gl.glColor3fv(col, 0);
		gl.glMaterialf(GL2.GL_FRONT, GL2.GL_SHININESS, 100f);
		gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_DIFFUSE, col, 0);
		gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_SPECULAR, col, 0);
	}

	public static void setMaterialRims(GL2 gl) {
		gl.glColor3fv(DARK_GRAY, 0);
		gl.glMaterialf(GL2.GL_FRONT, GL2.GL_SHININESS, 20f);
		gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_DIFFUSE, DARK_GRAY, 0);
		gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_SPECULAR, BLACK, 0);
	}

	// start needs work
	
	public static void setAsphaltMaterial(GL2 gl) {
		float[] mat_ambient = { 0.154f, 0.15f, 0.166f, 1f };
		float[] mat_diffuse = { 0.683f, 0.67f, 0.725f, 1f };
		float[] mat_specular = { 0.333f, 0.329f, 0.346f, 1f };
		float shine = 38.4f;
		gl.glMaterialf(GL2.GL_FRONT, GL2.GL_SHININESS, shine);
		gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_AMBIENT, mat_ambient, 0);
		gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_DIFFUSE, mat_diffuse, 0);
		gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_SPECULAR, mat_specular, 0);
	}

	public static void setWoodenBoxMaterial(GL2 gl) {
		float[] mat_ambient = { 0.4f, 0.4f, 0.4f, 1.0f };
		float[] mat_diffuse = { 0.714f, 0.428f, 0.181f, 1f };
		float[] mat_specular = { 0.394f, 0.272f, 0.167f, 1f };
		float shine = 25.6f;
		gl.glMaterialf(GL2.GL_FRONT, GL2.GL_SHININESS, shine);
		gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_AMBIENT, mat_ambient, 0);
		gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_DIFFUSE, mat_diffuse, 0);
		gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_SPECULAR, mat_specular, 0);
	}
	// end needs work
}
