package org.arabikitouhu.matrix;

import org.arabikitouhu.vector.Vector3;
import org.arabikitouhu.vector.Vector4;

public class Matrix {

	public static final Matrix Identity = new Matrix();

	/*
	 * { 0, 1, 2, 3,
	 *   4, 5, 6, 7,
	 *   8, 9, 10, 11,
	 *   12, 13, 14, 15 }
	 */
	protected float[] mats;

	public Matrix() {
		mats = new float[16];

		Identity();
	}

	public Matrix(float[] matrix) {
		mats = matrix;
	}


	public void Identity() {
		for(int i = 0; i < 16; i++) {
			mats[i] = 0.0f;
		}

		mats[0] = mats[5] = mats[10] = mats[15] = 1.0f;
	}

	public void SetTranslate(float x, float y, float z) {
		Identity();

		mats[3] = x;
		mats[7] = y;
		mats[11] = z;
	}

	public void SetScale(float x, float y, float z) {
		Identity();

		mats[0] = x;
		mats[5] = y;
		mats[10] = z;
	}

	/** X軸回転 */
	public void SetRotateX(float angle) {
		Identity();

		float rad = angle * 3.141592f / 180;
		float sin = (float)Math.sin(rad);
		float cos = (float)Math.cos(rad);

		mats[5] = mats[10] = cos;
		mats[6] = -sin;
		mats[9] = sin;
	}

	/** Y軸回転 */
	public void SetRotateY(float angle) {
		Identity();

		float rad = angle * 3.141592f / 180;
		float sin = (float)Math.sin(rad);
		float cos = (float)Math.cos(rad);

		mats[0] = mats[10] = cos;
		mats[2] = sin;
		mats[8] = -sin;
	}

	/** Z軸回転 */
	public void SetRotateZ(float angle) {
		Identity();

		float rad = angle * 3.141592f / 180;
		float sin = (float)Math.sin(rad);
		float cos = (float)Math.cos(rad);

		mats[0] = mats[5] = cos;
		mats[2] = -sin;
		mats[4] = sin;
		mats[10] = 0f;
	}

	/** 任意軸回転 */
	public void SetRotate(float angle, float x, float y, float z) {
		Identity();

		float rad = angle * 3.141592f / 180;
		float sin = (float)Math.sin(rad);
		float cos = (float)Math.cos(rad);

		mats[0] = x * x * (1f - cos) + cos;
		mats[1] = x * y * (1f - cos) - z * sin;
		mats[2] = z * x * (1f - cos) + y * sin;

		mats[4] = x * y * (1f - cos) + z * sin;
		mats[5] = y * y * (1f - cos) + cos;
		mats[6] = y * z * (1f - cos) - x * sin;

		mats[8] = z * x * (1f - cos) - y * sin;
		mats[9] = y * z * (1f - cos) + x * sin;
		mats[10] = z * z * (1f - cos) + cos;
	}

	public Vector4 GetVector4_HX() { return new Vector4(mats[0], mats[1], mats[2], mats[3]); }
	public Vector4 GetVector4_VX() { return new Vector4(mats[0], mats[4], mats[8], mats[12]); }
	public Vector4 GetVector4_HY() { return new Vector4(mats[4], mats[5], mats[6], mats[7]); }
	public Vector4 GetVector4_VY() { return new Vector4(mats[1], mats[5], mats[9], mats[13]); }
	public Vector4 GetVector4_HZ() { return new Vector4(mats[8], mats[9], mats[10], mats[11]); }
	public Vector4 GetVector4_VZ() { return new Vector4(mats[2], mats[6], mats[10], mats[14]); }
	public Vector4 GetVector4_HW() { return new Vector4(mats[12], mats[13], mats[14], mats[15]); }
	public Vector4 GetVector4_VW() { return new Vector4(mats[3], mats[7], mats[11], mats[15]); }

	public Vector3 GetVector3_HX() { return GetVector4_HX().GetVector3(); }
	public Vector3 GetVector3_VX() { return GetVector4_VX().GetVector3(); }
	public Vector3 GetVector3_HY() { return GetVector4_HY().GetVector3(); }
	public Vector3 GetVector3_VY() { return GetVector4_VY().GetVector3(); }
	public Vector3 GetVector3_HZ() { return GetVector4_HZ().GetVector3(); }
	public Vector3 GetVector3_VZ() { return GetVector4_VZ().GetVector3(); }
	public Vector3 GetVector3_HW() { return GetVector4_HW().GetVector3(); }
	public Vector3 GetVector3_VW() { return GetVector4_VW().GetVector3(); }

	public Vector3 GetVector3() {
		float[] vecOut = new float[4];
		Vector4 vecIn = new Vector4(0f, 0f, 0f, 1f);

		for(int i=0; i < 4; i++) {
			vecOut[i] = 0f;
			for(int j=0; j < 4; j++)
				vecOut[i] += vecIn.Get(j) * Get(i, j);
		}

		Vector4 vec = new Vector4(vecOut);
		return vec.Normalize().GetVector3();
	}

	public float Get(int x, int y) { return mats[x + y * 4]; }

	/** 掛け算 */
	public static Matrix Multiply(Matrix src1, Matrix src2) {
		Matrix dst = new Matrix();

		dst.mats[0] = Vector4.Multiply(src1.GetVector4_HX(), src2.GetVector4_VX()).Sum();
		dst.mats[1] = Vector4.Multiply(src1.GetVector4_HX(), src2.GetVector4_VY()).Sum();
		dst.mats[2] = Vector4.Multiply(src1.GetVector4_HX(), src2.GetVector4_VZ()).Sum();
		dst.mats[3] = Vector4.Multiply(src1.GetVector4_HX(), src2.GetVector4_VW()).Sum();
		dst.mats[4] = Vector4.Multiply(src1.GetVector4_HY(), src2.GetVector4_VX()).Sum();
		dst.mats[5] = Vector4.Multiply(src1.GetVector4_HY(), src2.GetVector4_VY()).Sum();
		dst.mats[6] = Vector4.Multiply(src1.GetVector4_HY(), src2.GetVector4_VZ()).Sum();
		dst.mats[7] = Vector4.Multiply(src1.GetVector4_HY(), src2.GetVector4_VW()).Sum();
		dst.mats[8] = Vector4.Multiply(src1.GetVector4_HZ(), src2.GetVector4_VX()).Sum();
		dst.mats[9] = Vector4.Multiply(src1.GetVector4_HZ(), src2.GetVector4_VY()).Sum();
		dst.mats[10] = Vector4.Multiply(src1.GetVector4_HZ(), src2.GetVector4_VZ()).Sum();
		dst.mats[11] = Vector4.Multiply(src1.GetVector4_HZ(), src2.GetVector4_VW()).Sum();
		dst.mats[12] = Vector4.Multiply(src1.GetVector4_HW(), src2.GetVector4_VX()).Sum();
		dst.mats[13] = Vector4.Multiply(src1.GetVector4_HW(), src2.GetVector4_VY()).Sum();
		dst.mats[14] = Vector4.Multiply(src1.GetVector4_HW(), src2.GetVector4_VZ()).Sum();
		dst.mats[15] = Vector4.Multiply(src1.GetVector4_HW(), src2.GetVector4_VW()).Sum();

		return dst;
	}

	/** 逆行列の取得 */
	public static Matrix Inverse(Matrix src) {
		Matrix dst = new Matrix();

		float[] m = src.mats;

		dst.mats[0]		= (m[5] * m[10] * m[15]) + (m[6] * m[11] * m[13]) + (m[7] * m[9] * m[14]) - (m[5] * m[11] * m[14]) - (m[6] * m[9] * m[15]) - (m[7] * m[10] * m[13]);
		dst.mats[1]		= (m[1] * m[11] * m[14]) + (m[2] * m[9] * m[15]) + (m[3] * m[10] * m[13]) - (m[1] * m[10] * m[15]) - (m[2] * m[11] * m[13]) - (m[3] * m[9] * m[14]);
		dst.mats[2]		= (m[1] * m[6] * m[15]) + (m[2] * m[7] * m[13]) + (m[3] * m[5] * m[14]) - (m[1] * m[7] * m[14]) - (m[2] * m[5] * m[15]) - (m[3] * m[6] * m[13]);
		dst.mats[3]		= (m[1] * m[7] * m[10]) + (m[2] * m[5] * m[11]) + (m[3] * m[6] * m[9]) - (m[1] * m[6] * m[11]) - (m[2] * m[7] * m[9]) - (m[3] * m[5] * m[10]);

		dst.mats[4]		= (m[4] * m[11] * m[14]) + (m[6] * m[8] * m[15]) + (m[7] * m[10] * m[12]) - (m[4] * m[10] * m[15]) - (m[6] * m[11] * m[12]) - (m[7] * m[8] * m[14]);
		dst.mats[5]		= (m[0] * m[10] * m[15]) + (m[2] * m[11] * m[12]) + (m[3] * m[8] * m[14]) - (m[0] * m[11] * m[14]) - (m[2] * m[8] * m[15]) - (m[3] * m[10] * m[12]);
		dst.mats[6]		= (m[0] * m[7] * m[14]) + (m[2] * m[4] * m[15]) + (m[3] * m[6] * m[12]) - (m[0] * m[6] * m[15]) - (m[2] * m[7] * m[12]) - (m[3] * m[4] * m[14]);
		dst.mats[7]		= (m[0] * m[6] * m[11]) + (m[2] * m[7] * m[8]) + (m[3] * m[4] * m[10]) - (m[0] * m[7] * m[10]) - (m[2] * m[4] * m[11]) - (m[3] * m[6] * m[8]);

		dst.mats[8]		= (m[4] * m[9] * m[15]) + (m[5] * m[11] * m[12]) + (m[7] * m[8] * m[13]) - (m[4] * m[11] * m[13]) - (m[5] * m[8] * m[15]) - (m[7] * m[9] * m[12]);
		dst.mats[9]		= (m[0] * m[11] * m[13]) + (m[1] * m[8] * m[15]) + (m[3] * m[9] * m[12]) - (m[0] * m[9] * m[15]) - (m[1] * m[11] * m[12]) - (m[3] * m[8] * m[13]);
		dst.mats[10]	= (m[0] * m[5] * m[15]) + (m[1] * m[7] * m[12]) + (m[3] * m[4] * m[13]) - (m[0] * m[7] * m[13]) - (m[1] * m[4] * m[15]) - (m[3] * m[5] * m[12]);
		dst.mats[11]	= (m[0] * m[7] * m[9]) + (m[1] * m[4] * m[11]) + (m[3] * m[5] * m[8]) - (m[0] * m[5] * m[11]) - (m[1] * m[7] * m[8]) - (m[3] * m[4] * m[9]);

		dst.mats[12]	= (m[4] * m[10] * m[13]) + (m[5] * m[8] * m[14]) + (m[6] * m[9] * m[12]) - (m[4] * m[9] * m[14]) - (m[5] * m[10] * m[12]) - (m[6] * m[8] * m[13]);
		dst.mats[13]	= (m[0] * m[9] * m[14]) + (m[1] * m[10] * m[12]) + (m[2] * m[8] * m[13]) - (m[0] * m[10] * m[13]) - (m[1] * m[8] * m[14]) - (m[2] * m[9] * m[12]);
		dst.mats[14]	= (m[0] * m[6] * m[13]) + (m[1] * m[4] * m[14]) + (m[2] * m[5] * m[12]) - (m[0] * m[5] * m[14]) - (m[1] * m[6] * m[12]) - (m[2] * m[4] * m[13]);
		dst.mats[15]	= (m[0] * m[5] * m[10]) + (m[1] * m[6] * m[12]) + (m[2] * m[4] * m[9]) - (m[0] * m[6] * m[9]) - (m[1] * m[4] * m[14]) - (m[2] * m[5] * m[8]);

		return dst;
	}
}
