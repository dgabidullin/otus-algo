object ChessBitboardMoves {

    fun getKingMoves(position: Int): ULong {
        val k: ULong = 1uL shl position
        val notA: ULong = 0xFEFEFEFEFEFEFEFEuL
        val ka = k and notA
        val notH: ULong = 0x7F7F7F7F7F7F7F7FuL
        val kh = k and notH
        return   ka shl 7  or (k shl 8) or (kh shl 9) or
                (ka shr 1)              or (kh shl 1) or
                (ka shr 9) or (k shr 8) or (kh shr 7)
    }

    fun getKnightMoves(position: Int): ULong {
        val knightBits  = 1uL shl position
        return ((knightBits shr 10) or (knightBits shl 6)  and 0x3F3F3F3F3F3F3F3FuL) or
               ((knightBits shr 15) or (knightBits shl 17) and 0xFEFEFEFEFEFEFEFEuL) or
               ((knightBits shr 17) or (knightBits shl 15) and 0x7F7F7F7F7F7F7F7FuL) or
               ((knightBits shr 6)  or (knightBits shl 10) and 0xFCFCFCFCFCFCFCFCuL)
    }

    fun popCnt(mask: ULong): Int {
        var mutatedMask = mask
        var cnt = 0
        while (mutatedMask > 0uL) {
            if ((mutatedMask and 1uL) == 1uL) {
                cnt++
            }
            mutatedMask = mutatedMask shr 1
        }
        return cnt
    }

    fun popCnt2(mask: ULong): Int {
        var mutatedMask = mask
        var cnt = 0
        while (mutatedMask > 0uL) {
            mutatedMask = mutatedMask and (mutatedMask - 1uL)
            cnt++
        }
        return cnt
    }
}
