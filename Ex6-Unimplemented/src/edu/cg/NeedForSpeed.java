package edu.cg;

import java.awt.Component;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.FPSAnimator;

import edu.cg.algebra.Vec;
import edu.cg.models.Track;
import edu.cg.models.Car.F1Car;

/**
 * An OpenGL 3D Game.
 *
 */
public class NeedForSpeed implements GLEventListener {
	private GameState gameState = null; // Tracks the car movement and orientation
	private F1Car car = null; // The F1 car we want to render
	private Vec carCameraTranslation = null; // The accumulated translation that should be applied on the car, camera
												// and light sources
	private Track gameTrack = null; // The game track we want to render
	private FPSAnimator ani; // This object is responsible to redraw the model with a constant FPS
	private Component glPanel; // The canvas we draw on.
	private boolean isModelInitialized = false; // Whether model.init() was called.
	private boolean isDayMode = true; // Indicates whether the lighting mode is day/night.
	// TODO: add fields as you want. For example:
	// - Car initial position (should be fixed).
	// - Camera initial position (should be fixed)

	public NeedForSpeed(Component glPanel) {
		this.glPanel = glPanel;
		gameState = new GameState();
		gameTrack = new Track();
		carCameraTranslation = new Vec(0.0);
		car = new F1Car();
	}

	@Override
	public void display(GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2();
		if (!isModelInitialized) {
			initModel(gl);
		}
		if (isDayMode) {
			// TODO: Setup background color when day mode is on (You can choose differnt
			// color)
			gl.glClearColor(0.52f, 0.824f, 1.0f, 1.0f);
		} else {
			// TODO: Setup background color when night mode is on (You can choose differnt
			// color)
			gl.glClearColor(0.0f, 0.0f, 0.32f, 1.0f);
		}
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
		gl.glMatrixMode(GL2.GL_MODELVIEW);
		gl.glLoadIdentity();
		// TODO: This is the flow in which we render the scene. You can use different
		// flow.
		// Step (1) You should update the accumulated translation that needs to be
		// applied on the car, camera and light sources.
		updateCarCameraTranslation(gl);
		// Step (2) Position the camera and setup its orientation
		setupCamera(gl);
		// Step (3) setup the lighting.
		setupLights(gl);
		// Step (4) render the car.
		renderCar(gl);
		// Step (5) render the track.
		renderTrack(gl);
	}

	private void updateCarCameraTranslation(GL2 gl) {
		Vec translationVec = gameState.getNextTranslation();
		carCameraTranslation = carCameraTranslation.add(translationVec);
		double dx = Math.max(carCameraTranslation.x, -7);
		carCameraTranslation.x = ((float)Math.min(dx, 7));
		
		if (Math.abs(carCameraTranslation.z) >= 510) {
			carCameraTranslation.z = (-(float)(Math.abs(carCameraTranslation.z) % 500));
			gameTrack.changeTrack(gl);
		}
	}

	private void setupCamera(GL2 gl) {

		GLU glu = new GLU();
		glu.gluLookAt(
				carCameraTranslation.x, 1.8 + carCameraTranslation.y, 2 + carCameraTranslation.z,
				carCameraTranslation.x, 1.5 + carCameraTranslation.y, -5 + carCameraTranslation.z, 0,
				0.7, -0.3);
	}

	private void setupLights(GL2 gl) {
		if (isDayMode) {
			gl.glDisable(GL2.GL_LIGHT1);
			setupSun(gl, GL2.GL_LIGHT0);
		} else {
			setupMoon(gl);
			float[] pos1 = { carCameraTranslation.x, 8 + carCameraTranslation.y,
					carCameraTranslation.z, 1 };
			setupSpotlight(gl, GL2.GL_LIGHT0, pos1);
			float[] pos2 = { carCameraTranslation.x, 8 + carCameraTranslation.y,
					-15 + carCameraTranslation.z, 1 };
			setupSpotlight(gl, GL2.GL_LIGHT1, pos2);
		}
	}

	private void setupSun(GL2 gl, int light) {
		float[] sunColor = { 1, 1, 1, 1 };
		float[] pos = { carCameraTranslation.x, 60 + carCameraTranslation.y,
				carCameraTranslation.z, 1 };
		gl.glLightfv(light, GL2.GL_SPECULAR, sunColor, 0);
		gl.glLightfv(light, GL2.GL_DIFFUSE, sunColor, 0);
		gl.glLightfv(light, GL2.GL_POSITION, pos, 0);
		gl.glLightfv(light, GL2.GL_AMBIENT, new float[] { 0.1f, 0.1f, 0.1f, 1 }, 0);
		gl.glEnable(light);
	}

	private void setupMoon(GL2 gl) {
		gl.glLightModelfv(GL2.GL_LIGHT_MODEL_AMBIENT, new float[] { 0.15f, 0.15f, 0.18f, 1 }, 0);
	}

	private void setupSpotlight(GL2 gl, int light, float[] pos) {
		float[] sunColor = { 0.85f, 0.85f, 0.85f, 1 };
		gl.glLightfv(light, GL2.GL_POSITION, pos, 0);
		gl.glLightf(light, GL2.GL_SPOT_CUTOFF, 75f);
		gl.glLightfv(light, GL2.GL_SPOT_DIRECTION, new float[] { 0, -1, 0 }, 0);
		gl.glLightfv(light, GL2.GL_SPECULAR, sunColor, 0);
		gl.glLightfv(light, GL2.GL_DIFFUSE, sunColor, 0);
		gl.glEnable(light);
	}

	private void renderTrack(GL2 gl) {
		// TODO: Render the track.
		// * Note: the track shouldn't be translated. It should be fixed.
		gl.glPushMatrix();
		gameTrack.render(gl);
		gl.glPopMatrix();
	}

	private void renderCar(GL2 gl) {

		gl.glPushMatrix();
		double carDirection = gameState.getCarRotation();
		// xdelta is zero hence redundant
		double yDelta = 0.15;
		double zDelta = -6.6;

		gl.glTranslated(carCameraTranslation.x, yDelta + carCameraTranslation.y, zDelta + carCameraTranslation.z);
		gl.glScaled(4, 4, 4);// scale the car uniformly
		gl.glRotated(90, 0, 0.5, 0); // turn the car towards the track

		// up date the turn of the car to the correct direction
		gl.glRotated(-carDirection, 0, 1, 0);
		car.render(gl);
		gl.glPopMatrix();
	}

	public GameState getGameState() {
		return gameState;
	}

	@Override
	public void dispose(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
		this.car.destroy(gl);
		this.gameTrack.destroy(gl);
	}

	@Override
	public void init(GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2();

		// Initialize display callback timer
		ani = new FPSAnimator(30, true);
		ani.add(drawable);
		glPanel.repaint();

		initModel(gl);
		ani.start();
	}

	public void initModel(GL2 gl) {
		// TODO: You can change OpenGL modes during implementation for debug purposes.
		// Remember to change OpenGL mode as it was before.
		gl.glCullFace(GL2.GL_BACK);
		gl.glEnable(GL2.GL_CULL_FACE);
		gl.glEnable(GL2.GL_NORMALIZE);
		gl.glEnable(GL2.GL_DEPTH_TEST);
		gl.glEnable(GL2.GL_LIGHTING);
		gl.glEnable(GL2.GL_SMOOTH);

		car.init(gl);
		gameTrack.init(gl);
		isModelInitialized = true;
	}

	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
		// TODO Setup the projection matrix here.
		// - It is recommended to use gluPerspective - with fovy 57.0
		GL2 gl = drawable.getGL().getGL2();
		GLU glu = new GLU();
		gl.glMatrixMode(GL2.GL_PROJECTION);
		gl.glLoadIdentity();
		glu.gluPerspective(57, (double) width / height, 1, 600);// last values are clipping params
	}

	/**
	 * Start redrawing the scene with 30 FPS
	 */
	public void startAnimation() {
		if (!ani.isAnimating())
			ani.start();
	}

	/**
	 * Stop redrawing the scene with 30 FPS
	 */
	public void stopAnimation() {
		if (ani.isAnimating())
			ani.stop();
	}

	public void toggleNightMode() {
		isDayMode = !isDayMode;
	}

}
