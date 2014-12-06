package org.arabikitouhu.ray;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.arabikitouhu.matrix.Matrix;
import org.arabikitouhu.vector.Vector3;
import org.lwjgl.BufferUtils;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;

public class Ray {

	private Vector3 pos, eye;
	private Vector3 near, far;

	public Ray(float[] near, float[] far) {
		this.near = new Vector3(near);
		this.far = new Vector3(far);
		Initialize();
	}

	public Ray(Vector3 near, Vector3 far) {
		this.near = near.ToCopy();
		this.far = far.ToCopy();
		Initialize();
	}

	protected void Initialize() {
		this.pos = this.near.ToCopy();
		this.eye = Vector3.Subtract(this.far, this.near).Normalize();
	}

	public Vector3 GetNear() { return this.near.ToCopy(); }
	public Vector3 GetFar() { return this.far.ToCopy(); }
	public Vector3 GetPos() { return this.pos.ToCopy(); }
	public Vector3 GetEye() { return this.eye.ToCopy(); }


	public static Ray CreateBetweenMouseFromEyes() {

		float mouseX = Mouse.getX();
		float mouseY = Mouse.getY();

		FloatBuffer model = BufferUtils.createFloatBuffer(16);
		FloatBuffer projection = BufferUtils.createFloatBuffer(16);
		IntBuffer viewport = BufferUtils.createIntBuffer(16);

		GL11.glGetFloat(GL11.GL_MODELVIEW_MATRIX, model);
		GL11.glGetFloat(GL11.GL_PROJECTION_MATRIX, projection);
		GL11.glGetInteger(GL11.GL_VIEWPORT, viewport);

		float viewportHeight = viewport.get(3); // = 600.0f
		float y = viewportHeight - mouseY;

		FloatBuffer posNear = BufferUtils.createFloatBuffer(16);
		FloatBuffer posFar = BufferUtils.createFloatBuffer(16);

		GLU.gluUnProject(mouseX, y, -1.5f, model, projection, viewport, posNear);
		GLU.gluUnProject(mouseX, y, -6.5f, model, projection, viewport, posFar);

		Matrix matNear = new Matrix(posNear.array());
		Matrix matFar = new Matrix(posFar.array());

		Ray ray = new Ray(matNear.GetVector3(), matFar.GetVector3());
		return ray;
	}
}
