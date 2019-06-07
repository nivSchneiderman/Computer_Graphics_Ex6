package edu.cg.models.Car;

import com.jogamp.opengl.GL2;

public class Materials {
	// TODO: Use this class to update the color of the vertexes when drawing.
	private static final float DARK_GRAY[] = { 0.2f, 0.2f, 0.2f };
	private static final float DARK_RED[] = { 0.25f, 0.01f, 0.01f };
	private static final float RED[] = { 0.7f, 0f, 0f };
	private static final float BLACK[] = { 0.05f, 0.05f, 0.05f };

	public static void SetMetalMaterial(GL2 gl, float[] color) {
		gl.glColor3fv(color, 0);
	}

	public static void SetBlackMetalMaterial(GL2 gl) {
		SetMetalMaterial(gl, BLACK);
	}

	public static void SetRedMetalMaterial(GL2 gl) {
		SetMetalMaterial(gl, RED);
	}

	public static void SetDarkRedMetalMaterial(GL2 gl) {
		SetMetalMaterial(gl, DARK_RED);
	}

	public static void SetDarkGreyMetalMaterial(GL2 gl) {
		SetMetalMaterial(gl, DARK_GRAY);
	}

	public static void setMaterialTire(GL2 gl) {
		float col[] = { .05f, .05f, .05f };
		gl.glColor3fv(col, 0);
	}

	public static void setMaterialRims(GL2 gl) {
		gl.glColor3fv(DARK_GRAY, 0);
	}

	// start needs work
	public static void setGreenMaterial(GL2 gl) {
		float[] mat_ambient = { 0.0215F, 0.1745F, 0.0215F, 1.0F };
		float[] mat_diffuse = { 0.07568F, 0.61424F, 0.07568F, 1.0F };
		float[] mat_specular = { 0.633F, 0.727811F, 0.633F, 1.0F };
		float shine = 128.0F;
		gl.glMaterialf(1028, 5633, shine);
		gl.glMaterialfv(1028, 4608, mat_ambient, 0);
		gl.glMaterialfv(1028, 4609, mat_diffuse, 0);
		gl.glMaterialfv(1028, 4610, mat_specular, 0);
	}

	public static void setAsphaltMaterial(GL2 gl) {
		float[] mat_ambient = { 0.15375F, 0.15F, 0.16625F, 1.0F };
		float[] mat_diffuse = { 0.68275F, 0.67F, 0.72525F, 1.0F };
		float[] mat_specular = { 0.332741F, 0.328634F, 0.346435F, 1.0F };
		float shine = 38.4F;
		gl.glMaterialf(1028, 5633, shine);
		gl.glMaterialfv(1028, 4608, mat_ambient, 0);
		gl.glMaterialfv(1028, 4609, mat_diffuse, 0);
		gl.glMaterialfv(1028, 4610, mat_specular, 0);
	}

	public static void setWoodenBoxMaterial(GL2 gl) {
		float[] mat_ambient = { 0.4F, 0.4F, 0.4F, 1.0F };
		float[] mat_diffuse = { 0.714F, 0.4284F, 0.18144F, 1.0F };
		float[] mat_specular = { 0.393548F, 0.271906F, 0.166721F, 1.0F };
		float shine = 25.6F;
		gl.glMaterialf(1028, 5633, shine);
		gl.glMaterialfv(1028, 4608, mat_ambient, 0);
		gl.glMaterialfv(1028, 4609, mat_diffuse, 0);
		gl.glMaterialfv(1028, 4610, mat_specular, 0);
	}
	// end needs work
}
