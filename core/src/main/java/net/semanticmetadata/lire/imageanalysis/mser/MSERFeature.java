/*
 * This file is part of the LIRe project: http://www.semanticmetadata.net/lire
 * LIRe is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * LIRe is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with LIRe; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 * We kindly ask you to refer the following paper in any publication mentioning Lire:
 *
 * Lux Mathias, Savvas A. Chatzichristofis. Lire: Lucene Image Retrieval –
 * An Extensible Java CBIR Library. In proceedings of the 16th ACM International
 * Conference on Multimedia, pp. 1085-1088, Vancouver, Canada, 2008
 *
 * http://doi.acm.org/10.1145/1459359.1459577
 *
 * Copyright statement:
 * --------------------
 * (c) 2002-2011 by Mathias Lux (mathias@juggle.at)
 *     http://www.semanticmetadata.net/lire
 */

package net.semanticmetadata.lire.imageanalysis.mser;

import net.semanticmetadata.lire.imageanalysis.Histogram;
import net.semanticmetadata.lire.imageanalysis.LireFeature;
import net.semanticmetadata.lire.utils.ConversionUtils;
import net.semanticmetadata.lire.utils.MetricsUtils;
import net.semanticmetadata.lire.utils.SerializationUtils;

import java.awt.image.BufferedImage;

/**
 * Feature describing an MSER
 * <p/>
 * Date: 27.03.2011
 * Time: 10:00:08
 *
 * @author Christine Keim, christine.keim@inode.at
 */
public class MSERFeature extends Histogram implements LireFeature {
    MSERGrowthHistory mser;

    public MSERFeature(MSERGrowthHistory maxStableExtremalRegion, float[] invariants) {
        this.mser = maxStableExtremalRegion;
        descriptor = invariants;
    }

    public MSERFeature() {
        mser = null;
    }

    public void extract(BufferedImage bimg) {
        throw new RuntimeException("Extraction not available here");
        // does nothing ....
    }

    public float getDistance(LireFeature feature) {
        if (!(feature instanceof MSERFeature)) return -1;
        return MetricsUtils.distL2(descriptor, ((MSERFeature) feature).descriptor);
    }

    public String getStringRepresentation() {
        throw new UnsupportedOperationException("not implemented due to performance issues");
    }

    public void setStringRepresentation(String s) {
        throw new UnsupportedOperationException("not implemented due to performance issues");
    }

    /**
     * Provides a much faster way of serialization.
     *
     * @return a byte array that can be read with the corresponding method.
     * @see net.semanticmetadata.lire.imageanalysis.CEDD#setByteArrayRepresentation(byte[])
     */
    public byte[] getByteArrayRepresentation() {
        return SerializationUtils.toByteArray(descriptor);
    }

    /**
     * Reads descriptor from a byte array. Much faster than the String based method.
     *
     * @param in byte array from corresponding method
     * @see net.semanticmetadata.lire.imageanalysis.CEDD#getByteArrayRepresentation
     */
    public void setByteArrayRepresentation(byte[] in) {
        descriptor = SerializationUtils.toFloatArray(in);
    }

    public void setByteArrayRepresentation(byte[] in, int offset, int length) {
        descriptor = SerializationUtils.toFloatArray(in, offset, length);
    }

    public double[] getDoubleHistogram() {
        return ConversionUtils.toDouble(descriptor);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < descriptor.length; i++) {
            float v = descriptor[i];
            sb.append(v);
            sb.append(' ');

        }
        return sb.toString();
    }
}
