package org.arabikitouhu.vector;

public class Vector4 {

	protected float[] vecs;

	public Vector4(float x, float y, float z, float w) {
		vecs = new float[] { x, y, z, w };
	}

	public Vector4(float[] vecOut) {
		vecs = vecOut;
	}

	public Vector3 GetVector3() { return new Vector3(vecs[0], vecs[1], vecs[2]); }

	public float Get(int index) { return vecs[index]; }

	/** 各要素を足した値 */
	public float Sum() {
		return vecs[0] + vecs[1] + vecs[2] + vecs[3];
	}


	public Vector4 Normalize() {
		float w = vecs[3] == 0f ?
				(vecs[0] + vecs[1] + vecs[2]) : vecs[3];
		return new Vector4(vecs[0] / w, vecs[1] / w, vecs[2] / w, 1f);
	}

	/** 掛け算 */
	public static Vector4 Multiply(Vector4 src1, Vector4 src2) {
		float x = src1.vecs[0] * src2.vecs[0];
		float y = src1.vecs[1] * src2.vecs[1];
		float z = src1.vecs[2] * src2.vecs[2];
		float w = src1.vecs[3] * src2.vecs[3];

		return new Vector4(x , y, z, w);
	}



}
