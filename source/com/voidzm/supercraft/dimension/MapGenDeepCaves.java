package com.voidzm.supercraft.dimension;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.MapGenCaves;

public class MapGenDeepCaves extends MapGenCaves {

	@Override
	protected void generateCaveNode(long par1, int par3, int par4, byte[] par5ArrayOfByte, double par6, double par8, double par10, float par12, float par13, float par14, int par15, int par16, double par17)
    {
        double d4 = (double)(par3 * 16 + 8);
        double d5 = (double)(par4 * 16 + 8);
        float f3 = 0.0F;
        float f4 = 0.0F;
        Random random = new Random(par1);

        if (par16 <= 0)
        {
            int j1 = this.range * 16 - 16;
            par16 = j1 - random.nextInt(j1 / 4);
        }

        boolean flag = false;

        if (par15 == -1)
        {
            par15 = par16 / 2;
            flag = true;
        }

        int k1 = random.nextInt(par16 / 2) + par16 / 4;

        for (boolean flag1 = random.nextInt(6) == 0; par15 < par16; ++par15)
        {
            double d6 = 1.5D + (double)(MathHelper.sin((float)par15 * (float)Math.PI / (float)par16) * par12 * 1.0F);
            double d7 = d6 * par17;
            float f5 = MathHelper.cos(par14);
            float f6 = MathHelper.sin(par14);
            par6 += (double)(MathHelper.cos(par13) * f5);
            par8 += (double)f6;
            par10 += (double)(MathHelper.sin(par13) * f5);

            if (flag1)
            {
                par14 *= 0.92F;
            }
            else
            {
                par14 *= 0.7F;
            }

            par14 += f4 * 0.1F;
            par13 += f3 * 0.1F;
            f4 *= 0.9F;
            f3 *= 0.75F;
            f4 += (random.nextFloat() - random.nextFloat()) * random.nextFloat() * 2.0F;
            f3 += (random.nextFloat() - random.nextFloat()) * random.nextFloat() * 4.0F;

            if (!flag && par15 == k1 && par12 > 1.0F && par16 > 0)
            {
                this.generateCaveNode(random.nextLong(), par3, par4, par5ArrayOfByte, par6, par8, par10, random.nextFloat() * 1.5F + 0.5F, par13 - ((float)Math.PI / 2F), par14 / 3.0F, par15, par16, 1.0D);
                this.generateCaveNode(random.nextLong(), par3, par4, par5ArrayOfByte, par6, par8, par10, random.nextFloat() * 1.5F + 0.5F, par13 + ((float)Math.PI / 2F), par14 / 3.0F, par15, par16, 1.0D);
                return;
            }

            if (flag || random.nextInt(4) != 0)
            {
                double d8 = par6 - d4;
                double d9 = par10 - d5;
                double d10 = (double)(par16 - par15);
                double d11 = (double)(par12 + 2.0F + 16.0F);

                if (d8 * d8 + d9 * d9 - d10 * d10 > d11 * d11)
                {
                    return;
                }

                if (par6 >= d4 - 16.0D - d6 * 2.0D && par10 >= d5 - 16.0D - d6 * 2.0D && par6 <= d4 + 16.0D + d6 * 2.0D && par10 <= d5 + 16.0D + d6 * 2.0D)
                {
                    int l1 = MathHelper.floor_double(par6 - d6) - par3 * 16 - 1;
                    int i2 = MathHelper.floor_double(par6 + d6) - par3 * 16 + 1;
                    int j2 = MathHelper.floor_double(par8 - d7) - 1;
                    int k2 = MathHelper.floor_double(par8 + d7) + 1;
                    int l2 = MathHelper.floor_double(par10 - d6) - par4 * 16 - 1;
                    int i3 = MathHelper.floor_double(par10 + d6) - par4 * 16 + 1;

                    if (l1 < 0)
                    {
                        l1 = 0;
                    }

                    if (i2 > 16)
                    {
                        i2 = 16;
                    }

                    if (j2 < 1)
                    {
                        j2 = 1;
                    }

                    if (k2 > 127)
                    {
                        k2 = 127;
                    }

                    if (l2 < 0)
                    {
                        l2 = 0;
                    }

                    if (i3 > 16)
                    {
                        i3 = 16;
                    }

                    boolean flag2 = false;
                    int j3;
                    int k3;

                    for (j3 = l1; !flag2 && j3 < i2; ++j3)
                    {
                        for (int l3 = l2; !flag2 && l3 < i3; ++l3)
                        {
                            for (int i4 = k2 + 1; !flag2 && i4 >= j2 - 1; --i4)
                            {
                                k3 = (j3 * 16 + l3) * 128 + i4;

                                if (i4 >= 0 && i4 < 128)
                                {
                                    if (par5ArrayOfByte[k3] == Block.waterMoving.blockID || par5ArrayOfByte[k3] == Block.waterStill.blockID)
                                    {
                                        flag2 = true;
                                    }

                                    if (i4 != j2 - 1 && j3 != l1 && j3 != i2 - 1 && l3 != l2 && l3 != i3 - 1)
                                    {
                                        i4 = j2;
                                    }
                                }
                            }
                        }
                    }

                    if (!flag2)
                    {
                        for (j3 = l1; j3 < i2; ++j3)
                        {
                            double d12 = ((double)(j3 + par3 * 16) + 0.5D - par6) / d6;

                            for (k3 = l2; k3 < i3; ++k3)
                            {
                                double d13 = ((double)(k3 + par4 * 16) + 0.5D - par10) / d6;
                                int j4 = (j3 * 16 + k3) * 128 + k2;
                                boolean flag3 = false;

                                if (d12 * d12 + d13 * d13 < 1.0D)
                                {
                                    for (int k4 = k2 - 1; k4 >= j2; --k4)
                                    {
                                        double d14 = ((double)k4 + 0.5D - par8) / d7;

                                        if (d14 > -0.7D && d12 * d12 + d14 * d14 + d13 * d13 < 1.0D)
                                        {
                                            byte b0 = par5ArrayOfByte[j4];

                                            if (b0 == Block.grass.blockID)
                                            {
                                                flag3 = true;
                                            }

                                            if (b0 == Block.stone.blockID || b0 == Block.dirt.blockID || b0 == Block.grass.blockID)
                                            {
                                                if (k4 < 10)
                                                {
                                                    par5ArrayOfByte[j4] = (byte)Block.lavaMoving.blockID;
                                                }
                                                else
                                                {
                                                    par5ArrayOfByte[j4] = 0;

                                                    if (flag3 && par5ArrayOfByte[j4 - 1] == Block.dirt.blockID)
                                                    {
                                                        par5ArrayOfByte[j4 - 1] = this.worldObj.getBiomeGenForCoords(j3 + par3 * 16, k3 + par4 * 16).topBlock;
                                                    }
                                                }
                                            }
                                        }

                                        --j4;
                                    }
                                }
                            }
                        }

                        if (flag)
                        {
                            break;
                        }
                    }
                }
            }
        }
    }
	
	protected void recursiveGenerate(World par1World, int offsetChunkX, int offsetChunkZ, int chunkX, int chunkZ, byte[] par6ArrayOfByte) {
		int iterations = this.rand.nextInt(this.rand.nextInt(20) + 1);
		if(this.rand.nextInt(7) != 0) { // This is the frequency that the generator tries to spawn a cave system. 10%.
            iterations = 0;
		}
		for(int i = 0; i < iterations; ++i) {
			double cavePosX = (double)(offsetChunkX * 16 + this.rand.nextInt(16));
			double cavePosY = (double)this.rand.nextInt(128);
			double cavePosZ = (double)(offsetChunkZ * 16 + this.rand.nextInt(16));
			int smallBranches = 1;
			if(this.rand.nextInt(3) == 0) { // Frequency of making this a large node. 25%.
				this.generateLargeCaveNode(this.rand.nextLong(), chunkX, chunkZ, par6ArrayOfByte, cavePosX, cavePosY, cavePosZ);
				smallBranches += this.rand.nextInt(5); // Number of additional small branches to connect to this one.
			}
			for(int j = 0; j < smallBranches; ++j) {
				float f = this.rand.nextFloat() * (float)Math.PI * 2.0F; // Random angle in radians between 0 and 2*pi.
				float f1 = (this.rand.nextFloat() - 0.5F) * 2.0F / 8.0F; // Random float between -0.125 and 0.125.
				float f2 = this.rand.nextFloat() * 3.0F + this.rand.nextFloat(); // Weighted float (originally) between 0.0 and 3.0.
				if(this.rand.nextInt(5) == 0) {
					f2 *= this.rand.nextFloat() * this.rand.nextFloat() * 6.0F + 1.0F; // Adjusts the weighted float upward by a random factor between 1.0 and 7.0.
				}
				this.generateCaveNode(this.rand.nextLong(), chunkX, chunkZ, par6ArrayOfByte, cavePosX, cavePosY, cavePosZ, f2, f, f1, 0, 0, 1.0D);
			}
		}
	}
	
}
